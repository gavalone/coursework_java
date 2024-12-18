package com.example.coursework;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.password.PasswordEncoder;


@Controller
public class AppController {
    @Autowired
    private FilmsService filmsService;

    @Autowired
    private SessionsService sessionsService;
    @Autowired
    private MyAppUserService myAppUserService;

    @Autowired
    private BasketService basketService;



    @RequestMapping("/")
    public String index(Model model, @Param("keyword") String keyword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = "Вы вошли как " + auth.getName();
        model.addAttribute("username", username);

        List<Films> filmsList = filmsService.ListAll(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("filmsList", filmsList);
        return "index";
    }


    @RequestMapping("/addfilm")
    public  String showNewStudentForm(Model model){
        Films films = new Films();
        model.addAttribute("films", films);
        return "add";
    }

    @RequestMapping(value = "/save1", method = RequestMethod.POST)
    public String saveFirmware(@ModelAttribute("goods") Films films, MultipartFile file) throws IOException {
        films.setImage(file.getBytes());
        filmsService.save(films);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteGoods(@PathVariable Long id){
        filmsService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditStudentForm(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit");
        Films films = filmsService.get(id);
        mav.addObject("films", films);
        return mav;
    }

    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public String saveFirmwar(@ModelAttribute("goods") Films films, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            Films existing = filmsService.get(films.getId());
            existing.setDescription(films.getDescription());
            existing.setYear(films.getYear());
            existing.setFilmname(films.getFilmname());
            existing.setDirector(films.getDirector());

        }
        else {
            films.setImage(file.getBytes());
            filmsService.save(films);
        }

        return "redirect:/";
    }


    @RequestMapping("/cartpage")
    public  String tocartpage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String un = auth.getName();
        model.addAttribute("un", un);
        model.addAttribute("cartList", basketService.ListAll());
        return "shopcart";
    }



    @PostMapping("/deletecart/{id}")
    public String deleteCarts(@PathVariable Long id){
        basketService.delete(id);
        return "redirect:/cartpage";
    }


    @RequestMapping("/addtocart/{id}")
    public  String tocart(@PathVariable(name = "id") Long id){
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        Sessions sessions = sessionsService.get(id);
        String gn = sessions.getFilmname();
        int pr = sessions.getPrice();
        int leftitem = sessions.getQuantity();

        sessions.setQuantity(leftitem-1);
        sessionsService.save(sessions);

        Basket basket = new Basket();
        basket.setFilmname(gn);
        basket.setPrice(pr);
        basket.setUsername(auth);
        basketService.save(basket);

        return "redirect:/tosessions";
    }

    @RequestMapping("/toconfirm")
    public  String toconfirm(){
        basketService.deletebyuser(SecurityContextHolder.getContext().getAuthentication().getName());
        return "confirm";
    }

    @RequestMapping("/tosessions")
    public  String tosessions(Model model, @Param("keyword") String keyword){
        List<Sessions> sessionsList = sessionsService.ListAll(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sessionsList", sessionsList);
        return "sessions";
    }


    boolean sorted = true;
    @RequestMapping("sort")
    public  String sortHomePage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = "Вы вошли как " + auth.getName();
        model.addAttribute("username", username);

        List<Sessions> sessionsList = sessionsService.ListAll(null);;
        sessionsList.sort(Comparator.comparing(Sessions::getPrice));

        if (sorted==false){
            sessionsList.sort(Comparator.comparing(Sessions::getPrice).reversed());
        }

        model.addAttribute("sessionsList", sessionsList);
        sorted = !sorted;
        return "sessions";
    }


    @RequestMapping("/addsessions")
    public  String addSessions(Model model){
        Sessions sessions = new Sessions();

        List<Films> filmsList =  filmsService.ListAll(null);

        model.addAttribute("filmsList", filmsList);
        model.addAttribute("sessions", sessions);
        return "add2";
    }

    @RequestMapping("/editsessions/{id}")
    public ModelAndView editsessions(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit2");
        Sessions sessions = sessionsService.get(id);
        mav.addObject("sessions", sessions);

        List<Films> filmsList =  filmsService.ListAll(null);
        mav.addObject("filmsList", filmsList);
        return mav;
    }

    @RequestMapping(value = "/savesessions", method = RequestMethod.POST)
    public String saveSessions(@ModelAttribute("sessions") Sessions sessions) throws IOException {
        sessionsService.save(sessions);
        return "redirect:/tosessions";
    }
    @RequestMapping(value = "/savesessions1/{id}", method = RequestMethod.POST)
    public String saveSessions1(@ModelAttribute("goods") Sessions sessions){
        sessionsService.save(sessions);
        return "redirect:/tosessions";
    }

    @PostMapping("/deletesessions/{id}")
    public String deleteSessions(@PathVariable Long id){
        sessionsService.delete(id);
        return "redirect:/tosessions";
    }

    @RequestMapping("/toabout")
    public  String toabout(){
        return "about";
    }


    @RequestMapping("toadminpanel")
    public String toadminpanel(Model model){
        List<Myappuser> myappuserList = myAppUserService.findAll();
        model.addAttribute("myappuserList", myappuserList);



        Map<String, Integer> dateMap = new HashMap<>();

        for (Myappuser myappuser : myappuserList) {
            String dateGood = myappuser.getRole();
            dateMap.put(dateGood, dateMap.getOrDefault(dateGood, 0) + 1);
        }

        List<List<Object>> dateCountMap = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : dateMap.entrySet()) {
            List<Object> subList = new ArrayList<>();
            subList.add(entry.getKey());
            subList.add(entry.getValue());
            dateCountMap.add(subList);
        }

        model.addAttribute("chartData", dateCountMap);


        return "adminpanel";
    }

    @PostMapping("/updateRole/{id}")
    public String updateRole(@PathVariable Long id, @RequestParam("role") String role) {
        Myappuser user = myAppUserService.get(id);
        if (user != null) {
            user.setRole(role);
            myAppUserService.save(user);
        }
        return "redirect:/toadminpanel";
    }


    @Autowired
    private MyAppUserRepository myAppUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping(value = "/req/signup", consumes = "application/x-www-form-urlencoded")
    public String createUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password) {
        Myappuser user = new Myappuser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        myAppUserRepository.save(user);
        return "login";
    }


    @GetMapping("/req/login")
    public String login(){
        return "login";
    }

    @GetMapping("/req/signup")
    public String signup(){
        return "signup";
    }

    @RequestMapping("tologout")
    public String toLogout(){
        return "redirect:/";
    }

    @GetMapping("/edit1/{id}")
    @ResponseBody
    public ResponseEntity<Films> callOpen(@PathVariable(name = "id") Long id) {
        Films films = filmsService.get(id);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/new1")
    @ResponseBody
    public ResponseEntity<Films> callOpen1() {
        Films films = new Films();
        if (films.getFilmname()==null) {return ResponseEntity.ok(films);}
        filmsService.save(films);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/edit2/{id}")
    @ResponseBody
    public ResponseEntity<Sessions> callOpen2(@PathVariable(name = "id") Long id) {
        Sessions sessions = sessionsService.get(id);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/new2")
    @ResponseBody
    public ResponseEntity<Sessions> callOpen3() {
        Sessions sessions = new Sessions();
        if (sessions.getFilmname()==null) {return ResponseEntity.ok(sessions);}
        sessionsService.save(sessions);
        return ResponseEntity.ok(sessions);
    }

}
