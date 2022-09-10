package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testeDepositoRetirada()
    {
        ContaMagica conta = new ContaMagica("100445-14", "Caio");

        //Teste deposito

        // Silver -> Silver
        conta.deposito(100);
        assertEquals(Categoria.SILVER, conta.getCategoria());
        assertEquals(100, conta.getSaldo(),0.01);

        //Silver -> Gold
        conta.deposito(100000);
        assertEquals(Categoria.GOLD, conta.getCategoria());
        assertEquals(101100, conta.getSaldo(),0.01);

        //Gold -> Gold
        conta.deposito(100);
        assertEquals(Categoria.GOLD, conta.getCategoria());
        assertEquals(101201, conta.getSaldo(),0.01);

        //Gold -> Platinum
        conta.deposito(100000);
        assertEquals(Categoria.PLATINUM, conta.getCategoria());
        assertEquals(203701, conta.getSaldo(),0.01);

        //Platinum -> Platinum
        conta.deposito(100);
        assertEquals(Categoria.PLATINUM, conta.getCategoria());
        assertEquals(203803.5, conta.getSaldo(),0.01);

        //Teste retirada

        //Platinum -> Platinum
        conta.retirada(103803.5);
        assertEquals(Categoria.PLATINUM, conta.getCategoria());
        assertEquals(100000, conta.getSaldo(),0.01);

        //Platinum -> Gold
        conta.retirada(1);
        assertEquals(Categoria.GOLD, conta.getCategoria());
        assertEquals(99999, conta.getSaldo(),0.01);

        //Gold -> Gold
        conta.retirada(74999);
        assertEquals(Categoria.GOLD, conta.getCategoria());
        assertEquals(25000, conta.getSaldo(),0.01);

        //Gold -> Silver
        conta.retirada(1);
        assertEquals(Categoria.SILVER, conta.getCategoria());
        assertEquals(24999, conta.getSaldo(),0.01);

         //Silver -> Silver
         conta.retirada(999);
         assertEquals(Categoria.SILVER, conta.getCategoria());
         assertEquals(24000, conta.getSaldo(),0.01);

    }
}
