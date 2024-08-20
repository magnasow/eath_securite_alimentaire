package com.eath.web;



import com.eath.Service.TextToSpeechService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/text-to-speech")
public class TextToSpeechController {

    @Autowired
    private TextToSpeechService textToSpeechService;

    @PostMapping("/convert")
    public String convertTextToSpeech(@RequestBody String text) {
        return textToSpeechService.convertTextToSpeech(text);
    }
}
