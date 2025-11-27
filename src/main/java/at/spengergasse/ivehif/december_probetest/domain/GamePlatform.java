package at.spengergasse.ivehif.december_probetest.domain;

// Admittedly, using an enumeration for specifying the game platform
// is not the smartest idea, as I might not want to touch my code base
// whenever I add a new video game from a new (or older) platform.
public enum GamePlatform {
    PC,
    PLAYSTATION,
    XBOX,
    SWITCH
}
