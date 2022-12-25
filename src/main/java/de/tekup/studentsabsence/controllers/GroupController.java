package de.tekup.studentsabsence.controllers;


import de.tekup.studentsabsence.entities.*;
import de.tekup.studentsabsence.enums.LevelEnum;
import de.tekup.studentsabsence.enums.SpecialityEnum;
import de.tekup.studentsabsence.holders.GroupSubjectHolder;
import de.tekup.studentsabsence.services.AbsenceService;
import de.tekup.studentsabsence.services.GroupService;
import de.tekup.studentsabsence.services.GroupSubjectService;
import de.tekup.studentsabsence.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/groups")
@AllArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final SubjectService subjectService;
    private final GroupSubjectService groupSubjectService;
    private final AbsenceService absenceService;

    @GetMapping({"", "/"})
    public String index(Model model) {
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "groups/index";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("levels", LevelEnum.values());
        model.addAttribute("specialities", SpecialityEnum.values());
        model.addAttribute("group", new Group());
        return "groups/add";
    }

    @PostMapping("/add")
    public String add(@Valid Group group, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("levels", LevelEnum.values());
            model.addAttribute("specialities", SpecialityEnum.values());
            return "groups/add";
        }

        groupService.addGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/{id}/update")
    public String updateView(@PathVariable long id,  Model model) {
        model.addAttribute("levels", LevelEnum.values());
        model.addAttribute("specialities", SpecialityEnum.values());
        model.addAttribute("group", groupService.getGroupById(id));
        return "groups/update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable long id, @Valid Group group, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("levels", LevelEnum.values());
            model.addAttribute("specialities", SpecialityEnum.values());
            return "groups/update";
        }
        groupService.updateGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
        groupService.deleteGroup(id);
        return "redirect:/groups";
    }

    @GetMapping("/{id}/show")
    public String show(@PathVariable long id, Model model) {
        Group group = groupService.getGroupById(id);

        model.addAttribute("group", group);
        model.addAttribute("groupSubjects",groupSubjectService.getSubjectsByGroupId(id));
        model.addAttribute("students",group.getStudents());
        model.addAttribute("absenceService", absenceService);

        group.getStudents().forEach(student -> {

        });

        return "groups/show";
    }

    @GetMapping("/{id}/add-subject")
    public String addSubjectView(Model model , @PathVariable Long id){
        model.addAttribute("groupSubjectHolder", new GroupSubjectHolder());
        model.addAttribute("group",groupService.getGroupById(id));
        model.addAttribute("subjects",subjectService.getAllSubjects());
        return "groups/add-subject";

    }

    @PostMapping("/{id}/add-subject")
    public String addSubject(@PathVariable Long id, @Valid GroupSubjectHolder groupSubjectHolder, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("group",groupService.getGroupById(id));
            model.addAttribute("subjects",subjectService.getAllSubjects());
            return "groups/add-subject";
        }

        Group group = groupService.getGroupById(id);
        groupSubjectService.addSubjectToGroup(group, groupSubjectHolder.getSubject(), groupSubjectHolder.getHours());
        return "redirect:/groups/"+id+"/add-subject";
    }

    @GetMapping("/{gid}/subject/{sid}/delete")
    public String deleteSubject(@PathVariable Long gid, @PathVariable Long sid){
        groupSubjectService.deleteSubjectFromGroup(gid, sid);
        return "redirect:/groups/"+gid+"/show";
    }

    @GetMapping("/{id}/add-absences")
    public String addAbsenceView(@PathVariable long id, Model model) {
        Group group = groupService.getGroupById(id);

        model.addAttribute("group", group);
        model.addAttribute("absence", new Absence());
        model.addAttribute("groupSubjects", groupSubjectService.getSubjectsByGroupId(id));
        model.addAttribute("students", group.getStudents());

        return "groups/add-absences";
    }

    @PostMapping("/{id}/add-absences")
    public String addAbsence(@PathVariable long id, @Valid Absence absence, BindingResult bindingResult, @RequestParam(value = "students", required = false) List<Student> students, Model model) {
        //TODO Complete the body of this method
        if (bindingResult.hasErrors()) {
            model.addAttribute("group", groupService.getGroupById(id));
            model.addAttribute("students", groupService.getGroupById(id).getStudents());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            model.addAttribute("absence", new Absence());
            return "groups/add-absences";
        }

        if (students != null) {
            students.forEach(student -> {
                Absence abs = new Absence();
                abs.setStartDate(absence.getStartDate());
                abs.setHours(absence.getHours());
                abs.setSubject(absence.getSubject());
                abs.setStudent(student);
                absenceService.addAbsence(abs);
            });
        }
        return "redirect:/groups/"+id+"/add-absences";
    }

    @GetMapping("/{id}/high")
    public String showHighestAbsenceRate(@PathVariable Long id, Model model) {
        List<GroupSubject> groupSubjects = groupSubjectService.getSubjectsByGroupId(id);
        Subject highestAbsenceRateSubject = null;
        float highestAbsenceRate = 0;


        for (GroupSubject groupSubject : groupSubjects) {
            Subject subject = groupSubject.getSubject();
            float hours = groupSubject.getHours();
            float absenceRate = absenceService.hoursCountByGroupAndSubject(id, subject.getId()) / hours;
            if (absenceRate > highestAbsenceRate) {
                highestAbsenceRate = absenceRate;
                highestAbsenceRateSubject = subject;
            }

        }
        model.addAttribute("highestAbsenceRateSubject", highestAbsenceRateSubject);

        return "groups/high-absence";
    }

    @GetMapping("/{id}/low")
    public String showLowestAbsenceRate(@PathVariable Long id, Model model) {
        List<GroupSubject> groupSubjects = groupSubjectService.getSubjectsByGroupId(id);
        Subject lowestAbsenceRateSubject = null;
        float lowestAbsenceRate = Float.MAX_VALUE;
        for (GroupSubject groupSubject : groupSubjects) {
            Subject subject = groupSubject.getSubject();
            float hours = groupSubject.getHours();
            float absenceRate = absenceService.hoursCountByGroupAndSubject(id, subject.getId()) / hours;
            if (absenceRate < lowestAbsenceRate) {
                lowestAbsenceRate = absenceRate;
                lowestAbsenceRateSubject = subject;
            }
        }

        model.addAttribute("lowestAbsenceRateSubject", lowestAbsenceRateSubject);

        return "groups/low-absence";
    }

}
