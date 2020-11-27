package org.example.controller;

import org.example.model.*;
import org.example.service.category.CategoryService;
import org.example.service.city.CityService;
import org.example.service.message.MessageService;
import org.example.service.specialist.SpecialistsService;
import org.example.service.user.UserService;
import org.example.service.userbucket.UserBucketService;
import org.example.service.userresume.UserResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class ResumeController {
    private final CategoryService categoryService;
    private final CityService cityService;
    private final SpecialistsService specialistsService;
    private final UserResumeService userResumeService;
    private final UserService userService;
    private final UserBucketService userBucketService;
    private final MessageService messageService;

    @Autowired
    public ResumeController(CategoryService categoryService, CityService cityService, SpecialistsService specialistsService, UserResumeService userResumeService, UserService userService, UserBucketService userBucketService, MessageService messageService) {
        this.categoryService = categoryService;
        this.cityService = cityService;
        this.specialistsService = specialistsService;
        this.userResumeService = userResumeService;
        this.userService = userService;
        this.userBucketService = userBucketService;
        this.messageService = messageService;
    }

    @GetMapping("/resume")
    public String getAllPage(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);

        return "resume";
    }

    @PostMapping("/resume")
    public String addResumeOfUser(@RequestParam(name = "title") String title,
                                  @RequestParam(name = "category") String category,
                                  @RequestParam(name = "price") Integer price,
                                  @RequestParam(name = "city") String city,
                                  @RequestParam(name = "content") String content,
                                  @RequestParam(name = "image") String image,
                                  @AuthenticationPrincipal User user) {

        Category curCategory = categoryService.getCategoryByName(category);
        City curCity = cityService.getCityByName(city);

        Specialists specialists = new Specialists(title, curCategory, content, price, curCity, new Date(), image);
        Specialists specialistsDB = specialistsService.addSpecialist(specialists);

        userResumeService.addUserResume(new UserResume(user, specialistsDB));

        return "resume";
    }

    @GetMapping("/myResumes")
    public String getAllResumes(@AuthenticationPrincipal User user,
                                Model model) {
        User userByUsername = userService.getUserByUsername(user.getUsername());
        List<Specialists> resumes = specialistsService.getAllSpecialistsFromUserResumeByUserId(userByUsername.getId());

        model.addAttribute("resumes", resumes);
        model.addAttribute("user", userByUsername);

        return "myResumes";
    }

    @PostMapping("/api/deleteResume")
    @ResponseBody
    public void deleteResume(@RequestParam(name = "specId") long id,
                             @AuthenticationPrincipal User user) {
        User userDB = userService.getUserByUsername(user.getUsername());
        Specialists specialist = specialistsService.getSpecialistById(id);

        if(userDB != null && specialist != null) {
            UserResume resume = userResumeService.getResume(userDB.getId(), specialist.getId());

            List<Message> messages = messageService.getAllMessagesBySpecId(specialist.getId());

            List<UserBucket> buckets = userBucketService.getAllUserBucketsBySpecialistId(id);

            if(resume != null) {
                userResumeService.deleteResume(resume);
                for (UserBucket userBucket : buckets) {
                    userBucketService.deleteSpecialistAndUser(userBucket);
                }

                for (Message m: messages) {
                    messageService.deleteMessage(m);
                }

                specialistsService.deleteSpecialist(specialist);
            }
        }
    }
}
