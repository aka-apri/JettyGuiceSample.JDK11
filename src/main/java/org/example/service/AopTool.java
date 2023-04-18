package org.example.service;

import java.util.function.Function;

public interface AopTool {
    <T> T userLoggedIn(Applyable<T> proc);
    <T> T transactional(Applyable<T> proc);
}
