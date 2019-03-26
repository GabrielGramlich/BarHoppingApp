package GetDrink;

import static GetDrink.DrinkingFunTime.rating;

public class GetRatings {
    public static void main(String[] args) { }

    public static Double getNewSimpleRating(Double currentRating) {
        Double updateValue = .125;
        if (rating == 2) {
            if (currentRating >= 1 + updateValue) {
                currentRating -= updateValue;
            }
        } else if (rating == 1) {
            if (currentRating >= 1 + (updateValue * 2)) {
                currentRating -= updateValue * 2;
            }
        } else if (rating == 4) {
            if (currentRating <= 9 - updateValue) {
                currentRating += updateValue;
            }
        } else if (rating == 5) {
            if (currentRating <= 9 - (updateValue * 2)) {
                currentRating += updateValue * 2;
            }
        }
        return currentRating;
    }

    public static Double getNewComplexRating(Integer difference, Integer minusOrPlus, Double currentRating) {
        Double updateValue = .125;
        switch (rating) {
            case 1:
                if (difference == 1 || difference == 2) {
                    updateValue *= 2;
                    currentRating = getNewComplexRatingPart2(minusOrPlus, currentRating, updateValue);
                } else if (difference == 3 || difference == 4) {
                    updateValue *= 4;
                    currentRating = getNewComplexRatingPart2(minusOrPlus, currentRating, updateValue);
                }
            case 2:
                if (difference == 1 || difference == 2) {
                    currentRating = getNewComplexRatingPart2(minusOrPlus, currentRating, updateValue);
                } else if (difference == 3 || difference == 4) {
                    updateValue *= 2;
                    currentRating = getNewComplexRatingPart2(minusOrPlus, currentRating, updateValue);
                }
            case 4:
                if (difference == 1 || difference == 2) {
                    currentRating = getNewComplexRatingPart2(minusOrPlus, currentRating, updateValue);
                } else if (difference == 3 || difference == 4) {
                    updateValue *= 2;
                    currentRating = getNewComplexRatingPart2(minusOrPlus, currentRating, updateValue);
                }
            case 5:
                if (difference == 1 || difference == 2) {
                    updateValue *= 2;
                    currentRating = getNewComplexRatingPart2(minusOrPlus, currentRating, updateValue);
                } else if (difference == 3 || difference == 4) {
                    updateValue *= 4;
                    currentRating = getNewComplexRatingPart2(minusOrPlus, currentRating, updateValue);
                }
        }
        return currentRating;
    }

    public static Double getNewComplexRatingPart2(Integer minusOrPlus, Double currentRating, Double updateValue) {
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
