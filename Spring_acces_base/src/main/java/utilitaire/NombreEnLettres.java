package utilitaire;

public class NombreEnLettres {

    private static final String[] UNITE = {"", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf"};
    private static final String[] DIX_A_VINGT = {"dix", "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept", "dix-huit", "dix-neuf"};
    private static final String[] DIZAINE = {"", "", "vingt", "trente", "quarante", "cinquante", "soixante", "soixante", "quatre-vingt", "quatre-vingt"};

    public static String convertirEnLettres(double nombre) {
        long partieEntiere = (long) nombre;
        int partieDecimale = (int) ((nombre - partieEntiere) * 100);

        String resultat = convertirPartieEntiereEnLettres(partieEntiere);

        if (partieDecimale > 0) {
            resultat += " virgule " + convertirPartieDecimaleEnLettres(partieDecimale);
        }

        return resultat;
    }

    private static String convertirPartieEntiereEnLettres(long partieEntiere) {
        if (partieEntiere == 0) {
            return "zéro";
        }

        return convertirMilliards(partieEntiere / 1_000_000_000) +
                convertirMillions((partieEntiere / 1_000_000) % 1_000) +
                convertirMille((partieEntiere / 1_000) % 1_000) +
                convertirCentaines(partieEntiere % 1_000);
    }

    private static String convertirPartieDecimaleEnLettres(int partieDecimale) {
        if (partieDecimale == 0) {
            return "zéro";
        }

        return convertirCentaines(partieDecimale);
    }

    private static String convertirMilliards(long milliards) {
        if (milliards == 0) {
            return "";
        }

        return convertirCentaines(milliards) + " milliard" + (milliards > 1 ? "s" : "") + " ";
    }

    private static String convertirMillions(long millions) {
        if (millions == 0) {
            return "";
        }

        return convertirCentaines(millions) + " million" + (millions > 1 ? "s" : "") + " ";
    }

    private static String convertirMille(long mille) {
        if (mille == 0) {
            return "";
        }

        return convertirCentaines(mille) + " mille ";
    }

    private static String convertirCentaines(long centaines) {
        if (centaines == 0) {
            return "";
        }

        String resultat = "";

        if (centaines >= 100) {
            resultat += UNITE[(int) (centaines / 100)] + " cent ";
            centaines %= 100;
        }

        if (centaines >= 10 && centaines <= 19) {
            resultat += DIX_A_VINGT[(int) (centaines % 10)] + " ";
        } else {
            resultat += DIZAINE[(int) (centaines / 10)] + " ";
            centaines %= 10;

            if (centaines > 0) {
                resultat += UNITE[(int) centaines] + " ";
            }
        }

        return resultat;
    }

    public static void main(String[] args) {
        double nombre = 123456789.12;
        String enLettres = convertirEnLettres(nombre);
        System.out.println(nombre + " en lettres : " + enLettres);
    }
}