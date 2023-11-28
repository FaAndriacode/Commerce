package ITU.Tovo.commercial.Models;

public class ConvertionLettre {
    private static final String[] units = {
        "", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix",
        "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept", "dix-huit", "dix-neuf"
    };

    private static final String[] tens = {
        "", "", "vingt", "trente", "quarante", "cinquante", "soixante", "soixante-dix", "quatre-vingts", "quatre-vingt-dix"
    };

    private static final String[] thousands = {
        "", "mille", "million", "milliard", "billion", "billiard", "trillion", "trilliard", "quadrillion", "quadrilliard"
    };

    public static String convertNumberToWords(float number) {
        if (number == 0) {
            return "zéro";
        }

        int intPart = (int) number;
        int decPart = (int) ((number - intPart) * 100);

        String words = "";

        int thousandCounter = 0;
        while (intPart > 0) {
            int triplet = intPart % 1000;
            if (triplet > 0) {
                if (words.length() > 0) {
                    words = convertTripletToWords(triplet) + " " + thousands[thousandCounter] + " " + words;
                } else {
                    words = convertTripletToWords(triplet) + " " + thousands[thousandCounter];
                }
            }
            intPart /= 1000;
            thousandCounter++;
        }

        if (decPart > 0) {
            words += " et " + convertTripletToWords(decPart) + " centimes";
        }

        return words.trim();
    }

    private static String convertTripletToWords(int number) {
        String words = "";

        int hundreds = number / 100;
        int tensUnits = number % 100;

        if (hundreds > 0) {
            words += units[hundreds] + " cent ";
        }

        if (tensUnits >= 20) {
            if (tensUnits >= 70 && tensUnits < 80) {
                words += tens[6] + "-";
                tensUnits -= 60;
            } else if (tensUnits >= 80 && tensUnits < 90) {
                words += tens[8] + "-";
                tensUnits -= 80;
            } else {
                words += tens[tensUnits / 10] + " ";
                tensUnits %= 10;
            }
        }


        if (tensUnits > 0) {
            words += units[tensUnits] + " ";
        }

        return words.trim();
    }
}
