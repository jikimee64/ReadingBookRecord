package com.software.development.softwaredevelopment.chapter6;

import com.software.development.softwaredevelopment.chapter6.domain.twoot.Twoot;

/**
 * Adapter interface for pushing information out to a UI port.
 */
public interface ReceiverEndPoint {
    void onTwoot(Twoot twoot);
}
