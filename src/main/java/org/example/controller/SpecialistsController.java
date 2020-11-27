package org.example.controller;

import org.example.model.Category;
import org.example.model.Specialists;
import org.example.model.User;
import org.example.service.category.CategoryService;
import org.example.service.specialist.SpecialistsService;
import org.example.service.user.UserService;
import org.example.service.userbucket.UserBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SpecialistsController {
    private final SpecialistsService specialistsService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final UserBucketService userBucketService;

    @Autowired
    public SpecialistsController(SpecialistsService specialistsService, CategoryService categoryService, UserService userService, UserBucketService userBucketService) {
        this.specialistsService = specialistsService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.userBucketService = userBucketService;
    }

    @PostMapping("/specialists/search")
    public String searchByCategory(@RequestParam(name = "category") String category,
                                   @RequestParam(name = "hiddenBlockId") String blockId) {
        if (category.equals("All")) {
            switch (blockId) {
                case "1":
                case "2":
                    return "redirect:/specialists/0/0/40";
                case "3":
                case "4":
                    return "redirect:/specialists/34/0/40";
                case "5":
                case "6":
                    return "redirect:/specialists/56/0/40";
                case "7":
                case "8":
                    return "redirect:/specialists/78/0/40";
                case "9":
                case "10":
                    return "redirect:/specialists/910/0/40";
                case "11":
                case "12":
                    return "redirect:/specialists/1112/0/40";
                default:
                    return "";
            }
        } else {
            Category categoryByName = categoryService.getCategoryByName(category);
            return "redirect:/specialists/" + categoryByName.getId() + "/0/40";
        }
    }

    @GetMapping("/specialists/{blockId}/{pageNo}/{pageSize}")
    public String getPaginatedSpecialists(@PathVariable int blockId,
                                          @PathVariable int pageNo,
                                          @PathVariable int pageSize,
                                          Model model,
                                          @AuthenticationPrincipal User user) {
        List<Specialists> list = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        String specialistName = "";
        int size = 0;

        switch (blockId) {
            case 0:
                list = specialistsService.findPaginated(1, 2, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(1L);
                size = size + specialistsService.getCountOfSpecialistsByCategoryId(2L);
                categories = categoryService.getCategoriesByIds(1L, 2L);
                specialistName = "Строительство и ремонт";
                break;
            case 1:
                list = specialistsService.findPaginated(1, 1, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(1L);
                categories = categoryService.getCategoriesByIds(1L, 2L);
                specialistName = "Строительство и ремонт";
                break;
            case 2:
                list = specialistsService.findPaginated(2, 2, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(2L);
                categories = categoryService.getCategoriesByIds(1L, 2L);
                specialistName = "Строительство и ремонт";
                break;
            case 34:
                list = specialistsService.findPaginated(3, 4, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(3L);
                size = size + specialistsService.getCountOfSpecialistsByCategoryId(4L);
                categories = categoryService.getCategoriesByIds(3L, 4L);
                specialistName = "Образование и курсы";
                break;
            case 3:
                list = specialistsService.findPaginated(3, 3, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(3L);
                categories = categoryService.getCategoriesByIds(3L, 4L);
                specialistName = "Образование и курсы";
                break;
            case 4:
                list = specialistsService.findPaginated(4, 4, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(4L);
                categories = categoryService.getCategoriesByIds(3L, 4L);
                specialistName = "Образование и курсы";
                break;
            case 56:
                list = specialistsService.findPaginated(5, 6, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(5L);
                size = size + specialistsService.getCountOfSpecialistsByCategoryId(6L);
                categories = categoryService.getCategoriesByIds(5L, 6L);
                specialistName = "Красота и здоровье";
                break;
            case 5:
                list = specialistsService.findPaginated(5, 5, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(5L);
                categories = categoryService.getCategoriesByIds(5L, 6L);
                specialistName = "Красота и здоровье";
                break;
            case 6:
                list = specialistsService.findPaginated(6, 6, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(6L);
                categories = categoryService.getCategoriesByIds(5L, 6L);
                specialistName = "Красота и здоровье";
                break;
            case 78:
                list = specialistsService.findPaginated(7, 8, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(7L);
                size = size + specialistsService.getCountOfSpecialistsByCategoryId(8L);
                categories = categoryService.getCategoriesByIds(7L, 8L);
                specialistName = "Интернет и компьютеры";
                break;
            case 7:
                list = specialistsService.findPaginated(7, 7, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(7L);
                categories = categoryService.getCategoriesByIds(7L, 8L);
                specialistName = "Интернет и компьютеры";
                break;
            case 8:
                list = specialistsService.findPaginated(8, 8, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(8L);
                categories = categoryService.getCategoriesByIds(7L, 8L);
                specialistName = "Интернет и компьютеры";
                break;
            case 910:
                list = specialistsService.findPaginated(9, 10, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(9L);
                size = size + specialistsService.getCountOfSpecialistsByCategoryId(10L);
                categories = categoryService.getCategoriesByIds(9L, 10L);
                specialistName = "Перевозки и автосервис";
                break;
            case 9:
                list = specialistsService.findPaginated(9, 9, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(9L);
                categories = categoryService.getCategoriesByIds(9L, 10L);
                specialistName = "Перевозки и автосервис";
                break;
            case 10:
                list = specialistsService.findPaginated(10, 10, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(10L);
                categories = categoryService.getCategoriesByIds(9L, 10L);
                specialistName = "Перевозки и автосервис";
                break;
            case 1112:
                list = specialistsService.findPaginated(11, 12, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(11L);
                size = size + specialistsService.getCountOfSpecialistsByCategoryId(12L);
                categories = categoryService.getCategoriesByIds(11L, 12L);
                specialistName = "Уборка";
                break;
            case 11:
                list = specialistsService.findPaginated(11, 11, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(11L);
                categories = categoryService.getCategoriesByIds(11L, 12L);
                specialistName = "Уборка";
                break;
            case 12:
                list = specialistsService.findPaginated(12, 12, pageNo, pageSize);
                size = specialistsService.getCountOfSpecialistsByCategoryId(12L);
                categories = categoryService.getCategoriesByIds(11L, 12L);
                specialistName = "Уборка";
                break;
        }

        model.addAttribute("specialists", list);
        model.addAttribute("count", size / pageSize);
        model.addAttribute("blockId", blockId);
        model.addAttribute("categories", categories);
        model.addAttribute("specialistName", specialistName);
        User userDB = userService.getUserByUsername(user.getUsername());
        model.addAttribute("user", userDB);

        List<Specialists> specialists = userBucketService.getSpecialistsByUserId(userDB.getId());
        model.addAttribute("personalSpecialists", specialists);

        return "specialistList";
    }
}
