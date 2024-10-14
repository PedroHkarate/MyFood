import easyaccept.EasyAccept;
import br.ufal.ic.p2.myfood.Facade;

public class Main {
    public static void main(String[] args) {
        String[] testFiles = {
                "tests/us1_1.txt",
                "tests/us1_2.txt",
                "tests/us2_1.txt",
                "tests/us2_2.txt",
                "tests/us3_1.txt",
                "tests/us3_2.txt",
                "tests/us4_1.txt",
                "tests/us4_2.txt"
        };

        for (String testFile : testFiles) {
            String[] eaArgs = {"br.ufal.ic.p2.myfood.Facade", testFile};
            EasyAccept.main(eaArgs);
        }
    }
}

/*

public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade();
        String[] testFiles = {
                "tests/us1_1.txt",
                "tests/us1_2.txt",
                "tests/us2_1.txt",
                "tests/us2_2.txt"
        };

        EasyAccept.main(new String[]{facade.getClass().getName(), testFiles[0]});
        facade.getSistema().imprimirUsuariosCadastrados();
        facade.getSistema().imprimirEmpresasCadastradas();

        EasyAccept.main(new String[]{facade.getClass().getName(), testFiles[1]});
        facade.getSistema().imprimirUsuariosCadastrados();
        facade.getSistema().imprimirEmpresasCadastradas();

        EasyAccept.main(new String[]{facade.getClass().getName(), testFiles[2]});
        facade.getSistema().imprimirUsuariosCadastrados();
        facade.getSistema().imprimirEmpresasCadastradas();

        EasyAccept.main(new String[]{facade.getClass().getName(), testFiles[3]});
        facade.getSistema().imprimirUsuariosCadastrados();
        facade.getSistema().imprimirEmpresasCadastradas();
    }
}

 */
