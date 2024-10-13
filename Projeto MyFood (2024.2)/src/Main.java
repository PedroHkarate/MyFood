import easyaccept.EasyAccept;

public class Main {
    public static void main(String[] args) {
        String[] args2 = {"br.ufal.ic.p2.myfood.Facade",
                "tests/us1_1.txt",
                "tests/us1_2.txt",
                "tests/us2_1.txt",
                "tests/us2_2.txt"
        };
        EasyAccept.main(args2);
    }
}

/*

versão alternativa

import easyaccept.EasyAccept;

public class Main {
    public static void main(String[] args) {
        String[] testFiles = {
                "tests/us1_1.txt",
                "tests/us1_2.txt"
        };

        for (String testFile : testFiles) {
            String[] eaArgs = {"br.ufal.ic.p2.myfood.Facade", testFile};
            EasyAccept.main(eaArgs);
        }
    }
}

 */