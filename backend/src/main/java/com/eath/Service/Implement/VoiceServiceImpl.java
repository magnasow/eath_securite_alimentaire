package com.eath.Service.Implement;



import com.eath.Service.SpeechToTextService;
import com.eath.Service.VoiceService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VoiceServiceImpl implements VoiceService {

    private final SpeechToTextService speechToTextService;

    public VoiceServiceImpl(SpeechToTextService speechToTextService) {
        this.speechToTextService = speechToTextService;
    }

    @Override
    public String convertVoiceToText(MultipartFile file) throws IOException {
        return speechToTextService.recognize(file.getBytes());
    }

    @Override
    public String executeCommand(String commandText) {
        // Example command execution logic
        if (commandText.contains("turn on the lights")) {
            return "Lights turned on";
        }
        // Add more command logic here
        return "Unknown command";
    }
}
