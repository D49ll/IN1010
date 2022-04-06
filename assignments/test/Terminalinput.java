import java.util.Scanner;

class Terminalinput {
    static final String EmptyString = "";

    private Scanner scan;

    public Terminalinput(Scanner scanner) {
        this.scan = scanner;
    }

    public int hentInteger(int min, int max) {
        return hentInteger(EmptyString, min, max);
    }

    public int hentInteger(String prompt, int min, int max) {
        prompt(prompt);

        while(true) {
            try {
                int tall = Integer.parseInt(scan.nextLine());
                if (tall < min || tall > max) {
                    System.out.println(String.format("Ugyldig verdi. Veriden må være mellom %d og %d. Prøv igjen.", min, max));
                    prompt(prompt);
                    continue;
                }
                return tall;
            } catch (Exception e) {
                System.out.println("Ugyldig verdi. Verdien må være et heltall. Prøv igjen.");
                prompt(prompt);
            }
        }
    }

    public double hentDouble(double min, double max) {
        return hentDouble(EmptyString, min, max);
    }

    public double hentDouble(String prompt, double min, double max) {
        prompt(prompt);

        while(true) {
            try {
                double tall = Double.parseDouble(scan.nextLine());
                if (tall < min || tall > max) {
                    System.out.println(String.format("Ugyldig verdi. Veriden må være mellom %f og %f. Prøv igjen.", min, max));
                    prompt(prompt);
                    continue;
                }
                return tall;
            } catch (Exception e) {
                System.out.println("Ugyldig input. Verdien må være et flyttall. Prøv igjen.");
                prompt(prompt);
            }
        }
    }

    public String hentString(String pattern) {
        return hentString(EmptyString, pattern);
    }

    public String hentString(String prompt, String pattern) {
        prompt(prompt);

        String data = scan.nextLine();
        while (!data.matches(pattern)) {
            System.out.println("Ugyldig format. Prøv igjen.");
            prompt(prompt);

            data = scan.nextLine();
        }
        
        return data;
    }

    public void prompt() {
        System.out.print("> ");
    }

    public void prompt(String p) {
        if ( p.length() == 0 ) {
            prompt();
        } else {
            System.out.print(String.format("[%s] > ", p));
        }
    }

    // For testing
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Terminalinput t = new Terminalinput(scan);

        System.out.print("oppgi et HELTALL: ");
        System.out.println(t.hentInteger(0, 10));

        System.out.print("oppgi et FLYTTALL: ");
        System.out.println(t.hentDouble(1.1, 9.3));

        System.out.print("oppgi fødselsnummer: ");
        System.out.println(t.hentString("[0-9]{11}"));
        
        scan.close();
    }
}
