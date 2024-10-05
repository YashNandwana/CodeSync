package com.CodeSyncBE.codeSync.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AI {
    private String model;
    private List<Prompt> messages;
    private int temperature;
    private int max_tokens;
}
