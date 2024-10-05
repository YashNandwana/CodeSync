package com.CodeSyncBE.codeSync.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prompt {
    private String role;
    private String content;
}