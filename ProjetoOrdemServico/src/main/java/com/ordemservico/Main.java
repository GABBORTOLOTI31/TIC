
package com.ordemservico;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Cliente cliente = new Cliente(
                1,
                "Gabriel Bortoloti",
                "123.456.789-00",
                "(16) 99999-9999",
                "gabriel@email.com",
                LocalDate.now(),
                "Rua Exemplo, 100"
        );

        Tecnico tecnico = new Tecnico(
                1,
                "Carlos Silva",
                "987.654.321-00",
                "(16) 98888-8888",
                "carlos@email.com",
                "Manutenção de Computadores",
                "TEC123"
        );

        Equipamento equipamento = new Equipamento(
                1,
                "Inspiron 15",
                "Dell",
                "ABC12345",
                "Não liga"
        );

        Material material1 = new Material(
                1,
                "Fonte ATX",
                "UN",
                250.00
        );

        Material material2 = new Material(
                2,
                "Memória RAM 8GB",
                "UN",
                180.00
        );

        OrdemDeServico os = new OrdemDeServico(
                1001,
                LocalDate.now(),
                "Troca de peças e manutenção",
                "EM ANDAMENTO",
                300.00,
                cliente,
                tecnico
        );

        os.adicionarEquipamento(equipamento);
        os.adicionarMaterial(material1);
        os.adicionarMaterial(material2);

        os.exibirDados();
    }
}
