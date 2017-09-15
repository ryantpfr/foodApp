package org.toepfer.foodApp.web;

import org.toepfer.foodApp.db.service.AuthenticationService;
import org.toepfer.foodApp.db.service.VotingService;
import org.toepfer.foodApp.web.bean.Restaurant;
import org.toepfer.foodApp.web.bean.ViewOptions;
import org.toepfer.foodApp.web.bean.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.security.Principal;
import java.util.List;


@Controller
public class WebController extends WebMvcConfigurerAdapter {

    public static final String HOME = "home";
    public static final String RESTAURANTS_KEY = "restaurants";

    @Autowired
    private VotingService votingService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/greeting").setViewName("greeting");
    }

    @GetMapping("/")
    public String sendHome(){
        return "redirect:"+HOME;
    }

    @GetMapping("/home")
    public String showForm(ViewOptions viewOptions, Model model) {

        viewOptions.setShowAuthFailMessage(false);
        viewOptions.setShowLoginForm(true);

        List<Restaurant> restaurants = votingService.getSessionRestaurants();

        model.addAttribute(RESTAURANTS_KEY,restaurants);

        return HOME;
    }

    @PostMapping("/home")
    public String vote(Principal principal, ViewOptions viewOptions, Vote vote, Model model) {

        if(vote != null) {
            votingService.vote(vote.getId(), principal.getName());
        }//no reason to do anything if vote is null

        List<Restaurant> restaurants = votingService.getSessionRestaurants();

        model.addAttribute(RESTAURANTS_KEY,restaurants);

        return HOME;
    }

    @GetMapping("/admin/new-session")
    public ModelAndView startSession(Principal principal) {

        List<Restaurant> restaurants = votingService.createSession();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/"+HOME);

        modelAndView.addObject(RESTAURANTS_KEY,restaurants);

        return modelAndView;
    }

}