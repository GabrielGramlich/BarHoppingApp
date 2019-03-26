public class testing {
    public static Double main(String[] args, Integer rating, Integer difference, Integer minusOrPlus, Double currentRating, Double updateValue) {
        switch (rating) {
            case 1:
                if (difference == 1 || difference == 2) {
                    updateValue *= 2;
                    currentRating = updateStuff(minusOrPlus, currentRating, updateValue);
                } else if (difference == 3 || difference == 4) {
                    updateValue *= 4;
                    currentRating = updateStuff(minusOrPlus, currentRating, updateValue);
                }
            case 2:
                if (difference == 1 || difference == 2) {
                    currentRating = updateStuff(minusOrPlus, currentRating, updateValue);
                } else if (difference == 3 || difference == 4) {
                    updateValue *= 2;
                    currentRating = updateStuff(minusOrPlus, currentRating, updateValue);
                }
            case 4:
                if (difference == 1 || difference == 2) {
                    currentRating = updateStuff(minusOrPlus, currentRating, updateValue);
                } else if (difference == 3 || difference == 4) {
                    updateValue *= 2;
                    currentRating = updateStuff(minusOrPlus, currentRating, updateValue);
                }
            case 5:
                if (difference == 1 || difference == 2) {
                    updateValue *= 2;
                    currentRating = updateStuff(minusOrPlus, currentRating, updateValue);
                } else if (difference == 3 || difference == 4) {
                    updateValue *= 4;
                    currentRating = updateStuff(minusOrPlus, currentRating, updateValue);
                }
        }
        return currentRating;
    }

    public static Double updateStuff(Integer minusOrPlus, Double currentRating, Double updateValue) {
        if (minusOrPlus == 0) {
            if (currentRating >= 1 + updateValue) {
                currentRating -= updateValue;
            }
        } else if (minusOrPlus == 2) {
            if (currentRating <= 9 - updateValue) {
                currentRating += updateValue;
            }
        }
        return currentRating;
    }
}
