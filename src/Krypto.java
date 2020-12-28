import java.math.BigInteger;
import java.util.Random;

public class Krypto {

    //zad1
    public static BigInteger zadanie1(BigInteger number, BigInteger kbit) {


        BigInteger rndBigInt;
        int kbitX = kbit.intValue();

//        int checkBit = number.bitLength();

        int checkBit = kbitX + 1;

        if (checkBit < kbitX) {
            System.out.println("Wprowadzona liczb powiada więcej bitów niż wskazany limit");
        }

        do {
            rndBigInt = new BigInteger(kbitX, new Random());
        }
        while (rndBigInt.compareTo(number) == -1
                && rndBigInt.compareTo(BigInteger.ONE.shiftRight(kbitX).subtract(BigInteger.ONE)) != -1);
        System.out.println("Zadanie 1: " + rndBigInt);
        return rndBigInt;
    }


    //zad2
    public static void zadanie2(BigInteger a, BigInteger b) {

        BigInteger primala = a;
        BigInteger primalb = b;


        System.out.println("a: " + a);
        System.out.println("b: " + b);

        BigInteger gca = a;
        BigInteger gcb = b;
        while (gca.compareTo(gcb) != 0) {
            if (gca.compareTo(gcb) > 0)
                gca = gca.subtract(gcb);
            else
                gcb = gcb.subtract(gca);
        }

        System.out.println("NWD to: " + gcb);

        BigInteger x = BigInteger.ZERO;
        BigInteger y = BigInteger.ONE;
        BigInteger lastx = BigInteger.ONE;
        BigInteger lasty = BigInteger.ZERO;
        BigInteger temp;

        while (b.compareTo(BigInteger.ZERO) != 0) {

            BigInteger q = a.divide(b);
            BigInteger r = a.mod(b);

            a = b;
            b = r;

            temp = x;

            x = lastx.subtract(q.multiply(x));

            lastx = temp;

            temp = y;

            y = lasty.subtract(q.multiply(y));

            lasty = temp;

        }
        System.out.println("x : " + lastx + " y :" + lasty);

        BigInteger odpowiednieModulo = BigInteger.ONE.mod(primalb);

        BigInteger counter = primalb;
        BigInteger wynik = BigInteger.ZERO;
        BigInteger elementOdwrotny = BigInteger.ZERO;

        while (wynik.compareTo(odpowiednieModulo) != 0) {
            counter = counter.subtract(BigInteger.ONE);
            wynik = (primala.multiply(counter)).mod(primalb);

            if (wynik.compareTo(BigInteger.ONE) == 0) {
                elementOdwrotny = counter;
            }
        }

        System.out.println("Element odwrotyn to : " + elementOdwrotny);
    }


    //1
    public static BigInteger generateRandomElement(int numberOfBits, BigInteger maxValue) throws Exception {
        BigInteger result;
        if (maxValue != null && maxValue.toString(2).length() < numberOfBits)
            throw new Exception("Max value is too small");
        if (maxValue == null) {
            do {
                result = new BigInteger(numberOfBits, new Random());
            } while (result.toString(2).length() != numberOfBits);
        } else {
            do {
                result = new BigInteger(numberOfBits, new Random());
            } while (result.toString(2).length() != numberOfBits || maxValue.compareTo(result) <= 0);
        }
        return result;
    }

    //2
    public static BigInteger getReverseNumber(BigInteger x, BigInteger n) throws Exception {
        if (n.compareTo(x) < 0) {
            throw new Exception("Second value must be greater than first");
        }
        BigInteger[] results = extendedEuclid(x, n);
        return BigInteger.ZERO.compareTo(results[1]) > 0 ? n.add(results[1]) : results[1];
    }

    private static BigInteger[] extendedEuclid(BigInteger x, BigInteger n) {
        if (BigInteger.ZERO.equals(n))
            return new BigInteger[]{x, BigInteger.ONE, BigInteger.ZERO};
        BigInteger[] result = extendedEuclid(n, x.mod(n));
        BigInteger d = result[0];
        BigInteger u = result[2];
        BigInteger v = d.subtract(x.multiply(u)).divide(n);
        return new BigInteger[]{d, u, v};
    }

    //zad3
    static BigInteger zadanie3(BigInteger x, BigInteger y, BigInteger p) {

        BigInteger res = new BigInteger("1");

        x = x.mod(p);

        if (x.equals(0)) return BigInteger.ZERO;

        while (y.compareTo(BigInteger.ZERO) > 0) {
            if ((y.and(BigInteger.ONE).equals(BigInteger.ONE)))
                res = (res.multiply(x)).mod(p);

            y = y.shiftRight(1);
            x = (x.multiply(x)).mod(p);
        }
        return res;
    }

    //zad4
    //reszta kwadratowa
    static boolean resztaKwadratowa(BigInteger n, BigInteger b) {
        BigInteger helper = (n.subtract(BigInteger.ONE)).divide(new BigInteger("2"));

        BigInteger power = new BigInteger(String.valueOf(b.modPow(helper, n)));
        //        BigInteger power = new BigInteger(String.valueOf(b.pow(helper.intValue()).mod(n)));

        if (power.compareTo(BigInteger.ONE) == 0) {
            return true;
        } else if (b.compareTo(BigInteger.ZERO) == 0) {
            return true;
        } else {
            return false;
        }
    }

    //zad5
    //pierwiastek kwadratowy
    static BigInteger pierwiastekKwadratowy(BigInteger n, BigInteger b) {

        BigInteger pattern = (n.add(BigInteger.ONE)).divide(new BigInteger("4"));
        BigInteger wynik = new BigInteger(String.valueOf(b.modPow(pattern, n)));
//        BigInteger wynik = new BigInteger(String.valueOf(b.pow(pattern.intValue())));

        return wynik;
    }

    //zad6
    static boolean fermatTest(BigInteger a) {
        Boolean wynik;

        wynik = a.isProbablePrime(1);

        return wynik;
    }

    ///////////////////////modul2

    //zad1
    static BigInteger zadanie1Modul2(BigInteger p) throws Exception {

        BigInteger randomA = generateRandomElement(280, p);
//        System.out.println("radnomA to: "+randomA);
//        System.out.println(randomA.bitLength());

        BigInteger randomB = generateRandomElement(280, p);
//        System.out.println("radnomB to: "+randomB);
//        System.out.println(randomB.bitLength());


        BigInteger resultP = new BigInteger("3");

        System.out.println("Sprawdzam czy p mod 4 jest równe 3 ");

        if ((p.mod(new BigInteger("4"))).compareTo(resultP) == 0) {
            System.out.println("P mod 4 jest rowne 3");
        } else {
            System.out.println("P mod 4 nie jest rowne 3");
            System.exit(0);
        }

        if (fermatTest(p)) {
            System.out.println("P jest liczbą pierwszą");
        } else {
            System.out.println("P nie jest liczba pierwsza");
            System.exit(0);
        }

//        BigInteger A = new BigInteger("251253937428096535381749615186704996827671824623713534194869720190371340712257034260791734664079");
//        BigInteger B = new BigInteger("468890980645387444649294984369242921655474302273793566616065070624554918380706937944353524200293");

//        BigInteger patternDelta = ((new BigInteger("4").multiply(A.pow(3))).add(new BigInteger("27").multiply(B.pow(2))));
        BigInteger patternDelta = ((new BigInteger("4").multiply(randomA.pow(3))).add(new BigInteger("27").multiply(randomB.pow(2))));

        BigInteger delta = new BigInteger(String.valueOf(patternDelta.mod(p)));

        System.out.println("A wynosi: " + randomA);
        System.out.println("B wynosi: " + randomB);
        System.out.println("Delta wynosi: " + delta);

        return delta;
    }

    //zad2
    static BigInteger zadanie2Modul2(BigInteger p, BigInteger a, BigInteger b) throws Exception {

//        BigInteger randomX = generateRandomElement(280, p);
        BigInteger randomX = new BigInteger("4");

        BigInteger patternX = (((randomX.pow(3)).add(a.multiply(randomX))).add(b)).mod(p);
        System.out.println("funckja wyniosi: " + patternX);

        if (resztaKwadratowa(p, new BigInteger(String.valueOf(patternX)))) {
            System.out.println("jest funkcja kwadratowa");
        } else {
            System.out.println("nie jest funkcja kwadratowa");
        }

        BigInteger y = pierwiastekKwadratowy(p, patternX);

        System.out.println("Pukntem losowym jest: (" + randomX + "," + y + ")");

        return new BigInteger("0");
    }

    //zad2 wersja z randomem
    static BigInteger zadanie2Modul2v2(BigInteger p, BigInteger a, BigInteger b) throws Exception {
        BigInteger randomX = null;
        BigInteger patternX = null;

        do {
            randomX = generateRandomElement(280, p);
            patternX = (((randomX.pow(3)).add(a.multiply(randomX))).add(b)).mod(p);
        } while (!resztaKwadratowa(p, new BigInteger(String.valueOf(patternX))));
//        System.out.println("x to: "+randomX);
//        System.out.println("funckja wynosi: " + patternX);

        BigInteger y = pierwiastekKwadratowy(p, patternX);

        System.out.println("Pukntem losowym jest: (" + randomX + "," + y + ")");

        return new BigInteger("0");
    }

    static boolean zadanie3Modul3(BigInteger p, BigInteger a, BigInteger b, BigInteger x, BigInteger y) {

        BigInteger sideRight = (((x.pow(3)).add(a.multiply(x))).add(b)).mod(p);
        System.out.println("right side = " + sideRight);

        BigInteger sideLeft = (y.pow(2)).mod(p);
        System.out.println("left side = " + sideLeft);

        return sideRight.compareTo(sideLeft) == 0;
    }

    static void zadanie4modul2(BigInteger p, BigInteger x, BigInteger y) {
        BigInteger newY = p.subtract(y);

        System.out.println("Pukntem odwrotnym jest: (" + x + "," + newY + ")");
    }

    static void zadanie5modul2(BigInteger x1, BigInteger y1, BigInteger x2, BigInteger y2, BigInteger a, BigInteger b, BigInteger p) throws Exception {
        BigInteger lambda = null;
        BigInteger x3 = null;
        BigInteger y3 = null;
        String O = "symbol O";
        if (x1.compareTo(x2) == 0 && y1.compareTo(y2) == 0) {
            lambda = (((new BigInteger("3").multiply(x1.pow(2))).add(a))
                    .multiply(getReverseNumber(
                            (new BigInteger("2").multiply(y1)), p)))
                    .mod(p);

            x3 = ((lambda.pow(2)).subtract(new BigInteger("2").multiply(x1))).mod(p);
            y3 = ((lambda.multiply(x1.subtract(x3))).subtract(y1)).mod(p);

            System.out.println("lamda to: " + lambda);
            System.out.println("x3: " + x3);
            System.out.println("y3: " + y3);
            System.out.println("Punkt P+P = (" + x3 + "," + y3 + ")");
        } else if (x1.compareTo(x2) != 0) {
            lambda = ((y2.subtract(y1)).multiply(
                    getReverseNumber(x2.subtract(x1), p)
            )).mod(p);

            x3 = (((lambda.pow(2)).subtract(x1)).subtract(x2)).mod(p);
            y3 = ((lambda.multiply(x1.subtract(x3))).subtract(y1)).mod(p);

            System.out.println("Punkt P+Q = (" + x3 + "," + y3 + ")");

//            System.out.println(lambda);
//            System.out.println(x3);
//            System.out.println(y3);
        } else if (x1.compareTo(x2) == 0 && y1.compareTo(y2.negate()) == 0) {
            System.out.println(O);
        } else {
            System.out.println("Punkt P + O to: (" + x1 + "," + y1 + ")");
        }
    }

    static void zadanie2Modul3(){
        
    }

    public static void main(String[] args) throws Exception {

//        System.out.println("-------------------------------------------");
//        zadanie1(new BigInteger("1300")
//                , new BigInteger("1300"));
////
//////        System.out.println("-------------------------------------------");
//////        zadanie2(new BigInteger("10"),new BigInteger("13") );
//
//
//        System.out.println("-------------------------------------------");
//        BigInteger x2 = new BigInteger("5968050664663944676194792716619308911312078316565917305592863744153060303493270013473926146269232599639013523950814867793996197165");
//        BigInteger y2 = new BigInteger("338314336688651740114692947137503107205344648165975596638401161881944042276045263409708436438961137413359255754153047065889611672");
//        BigInteger p2 = new BigInteger("6635534525562667737636364644775858866996999797997979797999979797979799797979799999999969969844435524455266365355553663773773737437");
//        System.out.println(" Zadanie3 - Potęga to:  " + zadanie3(x2, y2, p2));
//
//
//        System.out.println("-------------------------------------------");
//        System.out.println("Wynik zadania 4 to: " + resztaKwadratowa(new BigInteger("6635534525562667737636364644775858866996999797997979797999979797979799797979799999999969969844435524455266365355553663773773737437"), new BigInteger("5732412566107763797175466661208884176861639204565454632361259682023509640306700780534662815980174237947099185645064438918338136966")));
//
//        System.out.println("-------------------------------------------");
//        System.out.println("Wynik zadania 5to : " + pierwiastekKwadratowy(new BigInteger("6635534525562667737636364644775858866996999797997979797999979797979799797979799999999969969868972068101700999499016009898128078807"), new BigInteger("6325695318334357935087111918806706538484807050802312268625988374654510538936027779527552023051948512594798898001330025839748057936")));
//        System.out.println("-------------------------------------------");
////        System.out.println(fermatTest(new BigInteger("654561561356879113561")));
//        System.out.println("Zadanie 1: " + generateRandomElement(1300, new BigInteger("63741697998680920839567978705894425754990355172759963966162855836315393410467836082855666686281950392840356196613156919207792555342618582438685473330291309885767750827151728876069690955779086659437317893534688570905274443589902638013216472797712659738081459538590172336094781090181682939794166838100200266561736263261225462451183718991723020336840031700542542545493720187342812964528948054370")));
//        System.out.println("-------------------------------------------");
//        System.out.println(getReverseNumber(new BigInteger("4054235513256106650091285383070153230555464395227313342602540821231191579285249555142738160814135217816088233190614080454907675355"), new BigInteger("6635534525562667737636364644775858866996999797997979797999979797979799797979799999999969969844435524455266365355553663773773737437")));
//
//        System.out.println("-------------------------------------------");
//        System.out.println("Wynik zadania 4 to: " + resztaKwadratowa(new BigInteger("11"), new BigInteger("5")));
//        System.out.println("-------------------------------------------");
//        System.out.println("Wynik zadania 5to : " + pierwiastekKwadratowy(new BigInteger("11"), new BigInteger("71")));
//        System.out.println();
//        System.out.println("-------------------------------------------");
//        System.out.println("-------------------------------------------");

//        zadanie1Modul2(new BigInteger("1241282861446847839170228926026828716450703184678734522350890097344181755440518117399239645529943"));
        System.out.println("Zadanie 1");
        zadanie1Modul2(new BigInteger("1241282861446847839170228926026828716450703184678734522350890097344181755440518117399239645529943"));

        System.out.println("-------------------------------------------");
        System.out.println("Zadanie 2");

        zadanie2Modul2(new BigInteger("11"), new BigInteger("1"), new BigInteger("3"));
        System.out.println("-------------------------------------------");
        System.out.println("Zadanie 2");
        zadanie2Modul2v2(new BigInteger("1241282861446847839170228926026828716450703184678734522350890097344181755440518117399239645529943"),
                new BigInteger("251253937428096535381749615186704996827671824623713534194869720190371340712257034260791734664079"),
                new BigInteger("468890980645387444649294984369242921655474302273793566616065070624554918380706937944353524200293"));

        System.out.println("-------------------------------------------");
        System.out.println("Zadanie 3");
        System.out.println(zadanie3Modul3(new BigInteger("11"),
                new BigInteger("1"),
                new BigInteger("3"),
                new BigInteger("4"),
                new BigInteger("4")));

        System.out.println("-------------------------------------------");
        System.out.println("Zadanie 3");
        System.out.println(zadanie3Modul3(new BigInteger("1241282861446847839170228926026828716450703184678734522350890097344181755440518117399239645529943"),
                new BigInteger("251253937428096535381749615186704996827671824623713534194869720190371340712257034260791734664079"),
                new BigInteger("468890980645387444649294984369242921655474302273793566616065070624554918380706937944353524200293"),
                new BigInteger("1624701170741940441053085147380257829720259331712526020190735983486424969283746196455"),
                new BigInteger("97144628685544367442453217083744166426050941392104345568959870906220988557996176368027463712888")));

        System.out.println("-------------------------------------------");
        System.out.println("Zadanie 4");
        zadanie4modul2(new BigInteger("11"), new BigInteger("4"), new BigInteger("4"));

//        System.out.println("-------------------------------------------");
//        System.out.println("Zadanie 5");
//        zadanie5modul5(
//                new BigInteger("4"),
//                new BigInteger("4"),
//                new BigInteger("4"),
//                new BigInteger("4"),
//                new BigInteger("1"),
//                new BigInteger("3"),
//                new BigInteger("11")
//        );

//        System.out.println("-------------------------------------------");
//        System.out.println("Zadanie 5");
//        zadanie5modul2(
//                new BigInteger("4"),
//                new BigInteger("4"),
//                new BigInteger("7"),
//                new BigInteger("1"),
//                new BigInteger("1"),
//                new BigInteger("3"),
//                new BigInteger("11")
//        );

        System.out.println("-------------------------------------------");
        System.out.println("Zadanie 5");
        zadanie5modul2(
                new BigInteger("4"),
                new BigInteger("1"),
                new BigInteger("4"),
                new BigInteger("-1"),
                new BigInteger("1"),
                new BigInteger("3"),
                new BigInteger("11")
        );

        System.out.println(getReverseNumber(new BigInteger("3"), new BigInteger("11")));


    }
}

