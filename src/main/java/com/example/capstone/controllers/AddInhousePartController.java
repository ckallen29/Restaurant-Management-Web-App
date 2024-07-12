package com.example.capstone.controllers;

import com.example.capstone.domain.InhousePart;
import com.example.capstone.service.InhousePartService;
import com.example.capstone.service.InhousePartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 *
 *
 *
 *
 */
@Controller
public class AddInhousePartController{
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model theModel){
        InhousePart inhousepart=new InhousePart();
        theModel.addAttribute("inhousepart",inhousepart);
        return "InhousePartForm";
    }

    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult theBindingResult, Model theModel){
        theModel.addAttribute("inhousepart",part);
        if(theBindingResult.hasErrors()){
            return "InhousePartForm";
        }
        else{
        InhousePartService repo=context.getBean(InhousePartServiceImpl.class);
        InhousePart ip=repo.findById((int)part.getId());
            if (part.getInv() > part.getMaxInv()) {
                theBindingResult.rejectValue("inv", "inv.max", "Inventory cannot exceed max");
                return "InhousePartForm";
            }
            if (ip != null) {
                if (part.getInv() > part.getMaxInv()) {
                    theBindingResult.rejectValue("inv", "inv.max", "Inventory cannot exceed max");
                    return "InhousePartForm";
                }

                if (part.getInv() < part.getMinInv()) {
                    theBindingResult.rejectValue("inv", "inv.min", "Inventory must be above minimum");
                    return "InhousePartForm";
                }

                if (part.getMinInv() > part.getMaxInv()) {
                    theBindingResult.rejectValue("minInv", "inv.min", "Minimum must be less than max");
                    return "InhousePartForm";
                }

                if (part.getMaxInv() < part.getMinInv()) {
                    theBindingResult.rejectValue("maxInv", "inv.max", "Max must be above minimum");
                    return "InhousePartForm";
                }
            }

            if(ip!=null)part.setProducts(ip.getProducts());
            repo.save(part);

        return "confirmationaddpart";}
    }

}
