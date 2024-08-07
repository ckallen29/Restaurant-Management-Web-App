package com.example.capstone.controllers;

import com.example.capstone.domain.OutsourcedPart;
import com.example.capstone.domain.Part;
import com.example.capstone.service.OutsourcedPartService;
import com.example.capstone.service.OutsourcedPartServiceImpl;
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
public class AddOutsourcedPartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddOutPart")
    public String showFormAddOutsourcedPart(Model theModel){
        Part part=new OutsourcedPart();
        theModel.addAttribute("outsourcedpart",part);
        return "OutsourcedPartForm";
    }

    @PostMapping("/showFormAddOutPart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part, BindingResult bindingResult, Model theModel){
        theModel.addAttribute("outsourcedpart",part);
        if(bindingResult.hasErrors()){
            return "OutsourcedPartForm";
        }
        else{
        OutsourcedPartService repo=context.getBean(OutsourcedPartServiceImpl.class);
        OutsourcedPart op=repo.findById((int)part.getId());
        /*
        if (part.getInv() > part.getMaxInv()) {
            bindingResult.rejectValue("inv", "inv.max", "Inventory cannot exceed max");
            return "OutsourcedPartForm";
        }
        if (op != null) {
            if (part.getInv() > part.getMaxInv()) {
                bindingResult.rejectValue("inv", "inv.max", "Inventory cannot exceed max");
                return "OutsourcedPartForm";
            }

            if (part.getInv() < part.getMinInv()) {
                bindingResult.rejectValue("inv", "inv.min", "Inventory must be above minimum");
                return "OutsourcedPartForm";
            }

            if (part.getMinInv() > part.getMaxInv()) {
                bindingResult.rejectValue("minInv", "inv.min", "Minimum must be less than max");
                return "OutsourcedPartForm";
            }

            if (part.getMaxInv() < part.getMinInv()) {
                bindingResult.rejectValue("maxInv", "inv.max", "Max must be above minimum");
                return "OutsourcedPartForm";
            }
        }

         */

        if(op!=null)part.setProducts(op.getProducts());
            repo.save(part);
        return "confirmationaddpart";}
    }



}
