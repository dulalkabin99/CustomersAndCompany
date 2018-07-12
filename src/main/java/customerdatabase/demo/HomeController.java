package customerdatabase.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CompanyRepo companyRepo;

    @GetMapping("/")
    public String Home(Model model) {
        return "home";
    }


    @GetMapping("/customerlist")
    public String ShowAllCustomer(Model model) {
        model.addAttribute("emp", customerRepo.findAll());
        return "customerlist";
    }
    @PostMapping("/listbylastname")
    public String ShowCustomerByLastName(HttpServletRequest request, Model model) {
        String laName=request.getParameter("lastname");
        model.addAttribute("search",laName);
        model.addAttribute("lncus", customerRepo.findAllByLastnameContaining(laName));
        return "employeebylastname";
    }

    @PostMapping("/listbyState")
    public String showCustomersInStates(HttpServletRequest request, Model model) {
        String state=request.getParameter("state");
        model.addAttribute("search",state);
        model.addAttribute("stcus", customerRepo.findAllByStateContaining(state));
        return "customerbystate";
    }

    @GetMapping("/addcustomer")
    public String addCustomer(Model model) {
        model.addAttribute("options", companyRepo.findAll());
        model.addAttribute("customer", new Customer());
        return "addcustomer";
    }


    @PostMapping("/processcustomer")
    public String processForm(@Valid @ModelAttribute("customer") Customer customer,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "addcustomer";
        }

        customerRepo.save(customer);
        return "redirect:/";

    }

    @RequestMapping("/addcompany")
    public String addCompany(Model model) {

        model.addAttribute("companya", new Companya());
        return "addcompany";
    }

    @PostMapping("/processcompany")
    public String processCompany(@Valid @ModelAttribute("companya") Companya companya,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "addcompany";
        }
        companyRepo.save(companya);
        return "redirect:/";
    }
    @GetMapping("/updatecustomer")
    public String updateCustomer(Model model) {
        model.addAttribute("emp", customerRepo.findAll());
        return "updatecustomer";
}
}
