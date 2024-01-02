package com.CodeSyncBE.codeSync.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebRtcPayload {
    private String roomId;
    private String signalData;
}
