package com.lucadani;

public enum Difficulty {
    AMATOR(1, 20, 10, 90, "Warm up game. Relax, you have a lot of time and attemps!", "Well done! It was impossible to lose!", "Oops... how did you manage to lose at amator difficulty?"),
    EASY(1, 50, 7, 60, "We are starting easily. Let's see how fast you guess the number!", "Nice, you won without much trouble!", "You lost, but don't worry, try again!"),
    MEDIUM(1, 100, 5, 45, "Now the game is becoming interesting!", "Congrats! You won at medium difficulty!", "You lost, but you can try again, it's fair-play!"),
    HARD(1, 500, 3, 30, "Only the brave players try this! Time is up very fast...", "Respect! You just did something that only few players can do!", "You lost, but at least you tried!"),
    LEGENDARY(1, 1000, 2, 20, "Only a master can win at legendary difficulty!", "Wow, you just won at legendary difficulty!", "Unsurprinsingly, the legendary difficulty doesn't forgive anyone!");

    private final int mini;
    private final int maxi;
    private final int attempts;
    private final int timeLimit;
    private final String startMessage;
    private final String winMessage;
    private final String loseMessage;

    Difficulty(int mini, int maxi, int attempts, int timeLimit, String startMessage, String winMessage, String loseMessage) {
        this.mini = mini;
        this.maxi = maxi;
        this.attempts = attempts;
        this.timeLimit = timeLimit;
        this.startMessage = startMessage;
        this.winMessage = winMessage;
        this.loseMessage = loseMessage;
    }

    public int getMini() {
        return mini;
    }

    public int getMaxi() {
        return maxi;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public String getStartMessage() {
        return startMessage;
    }

    public String getWinMessage() {
        return winMessage;
    }

    public String getLoseMessage() {
        return loseMessage;
    }
}
