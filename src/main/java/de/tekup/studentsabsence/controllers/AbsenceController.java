package de.tekup.studentsabsence.controllers;

import de.tekup.studentsabsence.entities.Absence;
import de.tekup.studentsabsence.entities.Student;
import de.tekup.studentsabsence.services.AbsenceService;
import de.tekup.studentsabsence.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/absence")
@AllArgsConstructor
public class AbsenceController {
    private final AbsenceService absenceService;

    @GetMapping("/eliminate")
    public String getAbsence(Model model) {

        List<Float> nameList= absenceService.getAllAbsences().stream().map(x->x.getHours()).collect(Collectors.toList());
        List<String> absenceList = absenceService.getAllAbsences().stream().map(x-> x.getStudent().getFirstName()).collect(Collectors.toList());
        // List<Absence> absenceList = subjectService.getAllSubjects().stream().map(x-> x.getAbsence()).collect(Collectors.toList());
        model.addAttribute("name", nameList);
        model.addAttribute("id", absenceList);
        return "eliminer";
    }

}