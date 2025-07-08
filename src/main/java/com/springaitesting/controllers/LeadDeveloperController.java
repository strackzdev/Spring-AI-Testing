package com.springaitesting.controllers;

import com.springaitesting.agents.LeadDeveloperAgent;
import com.springaitesting.dtos.AiRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/lead-developer")
public class LeadDeveloperController {

    private final LeadDeveloperAgent leadDeveloperAgent;

    public LeadDeveloperController(LeadDeveloperAgent leadDeveloperAgent) {
        this.leadDeveloperAgent = leadDeveloperAgent;
    }

    @PostMapping(value = "/ask-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> askLeadDeveloperStreamed(@RequestBody AiRequest request) {
        return leadDeveloperAgent.investigateStreamed(request.question);
    }

    @PostMapping("/ask-full")
    public String askDetective(@RequestBody AiRequest aiRequest) {
        return leadDeveloperAgent.investigateFull(aiRequest.question);
    }
}
