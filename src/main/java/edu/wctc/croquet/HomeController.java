package edu.wctc.croquet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;

@Controller
public class HomeController {
    @Value("classpath:croquetRules.json")
    private Resource croquetRules;
    @Value("classpath:croquetGlossary.json")
    private Resource croquetGlossary;
    private String[] rules;
    private GlossaryTerm[] terms;

    @PostConstruct
    private void initData() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            rules = mapper.readValue(
                    croquetRules.getFile(),
                    String[].class);
        } catch (IOException e) {
            e.printStackTrace();
            rules = new String[0];
        }

        /**
         * Uncomment this section when you're ready to test your GlossaryTerm class.
         */
//        try {
//            terms = mapper.readValue(
//                    croquetGlossary.getFile(),
//                    GlossaryTerm[].class);
//        } catch (IOException e) {
//            e.printStackTrace();
//            terms = new GlossaryTerm[0];
//        }
    }

    @RequestMapping("/rules")
    public String showRulesPage(Model model) {
        /**
         * Add the array of Strings to the model
         */

        return "croquet-rules";
    }

    @RequestMapping("/terms")
    public String showGlossaryPage(Model model) {
        /**
         * Add the array of GlossaryTerm objects to the model
         */

        return "croquet-glossary";
    }

    @RequestMapping("/")
    public String showHomePage(Model model) {
        return "index";
    }
}
