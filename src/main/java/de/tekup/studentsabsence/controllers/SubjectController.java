package de.tekup.studentsabsence.controllers;

import de.tekup.studentsabsence.entities.Group;
import de.tekup.studentsabsence.entities.Student;
import de.tekup.studentsabsence.entities.Subject;
import de.tekup.studentsabsence.services.AbsenceService;
import de.tekup.studentsabsence.services.GroupService;
import de.tekup.studentsabsence.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/subjects")
@AllArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    private final AbsenceService absenceService;
    private final GroupService groupService;


    @GetMapping({"", "/"})
    public String index(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subjects/index";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("subject", new Subject());
        return "subjects/add";
    }

    @PostMapping("/add")
    public String add(@Valid Subject subject, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "subjects/add";
        }

        subjectService.addSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/{id}/update")
    public String updateView(@PathVariable Long id, Model model) {
        model.addAttribute("subject", subjectService.getSubjectById(id));
        return "subjects/update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @Valid Subject subject, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "subjects/update";
        }

        subjectService.updateSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {

        subjectService.deleteSubject(id);
        return "redirect:/subjects";
    }

    @GetMapping("/{id}/show")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("subject", subjectService.getSubjectById(id));
        return "subjects/show";
    }

    @GetMapping("/elimination")
    public String showSubjectSelectionForm(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subjects/select-subject";
    }

    @PostMapping("/elimination")
    public String submitSubject(@ModelAttribute("subjectId") Long subjectId, Model model) {

        Subject subject = subjectService.getSubjectById(subjectId);
        List<Group> groups = groupService.getAllGroups();
        List<Student> eliminatedStudents = new ArrayList<>();

        for (Group group : groups) {
            List<Student> students = group.getStudents();
            for (Student student : students) {
                long sid = student.getSid();
                float totalHoursAbsent = absenceService.hoursCountByStudentAndSubject(sid, subjectId);
                if (totalHoursAbsent > 7) {
                    eliminatedStudents.add(student);
                }
            }
        }

        model.addAttribute("subject", subject);
        model.addAttribute("eliminatedStudents", eliminatedStudents);
        model.addAttribute("selectedSubject", subject);
        // Return the name of the view template
        return "subjects/eliminated-students";
    }


}
