package com.example;

public class ContaMagica {
    private String numero;
    private String nomeCorrentista;
    private double saldo;
    private Categoria categoria;

    public ContaMagica(String numero, String nomeCorrentista) {
        this.numero = numero;
        this.nomeCorrentista = nomeCorrentista;
        this.saldo = 0.0; 
        this.categoria = Categoria.SILVER;
        verificaNome(nomeCorrentista);
        verificaNroConta(numero);
     }

    public String getNumeroConta(){
        return numero;
    }

    public String getNomeCorrentista(){
        return nomeCorrentista;
    }

    public double getSaldo(){
        return saldo;
    }

    public Categoria getCategoria(){
        return categoria;
    }

    public boolean deposito(double valor){
        //Caso seja depositado um valor negativo é invalidado
        if (valor <= 0.0){
            return false;
        }

        //Caso a conta esteja no SILVER
        if (categoria == Categoria.SILVER){
            //Caso a conta passe para o GOLD
            if (saldo + valor >= 50000){
                saldo += valor + valor * 0.01; //Deposito mais 1%
                categoria = Categoria.GOLD;
                return true;
            }else{
                saldo += valor;
            return true;
            }
        }

        //Caso a conta esteja no GOLD
        if (categoria == Categoria.GOLD){
            //Caso a conta passe para o PLATINUM
            if(saldo + valor >= 200000){
                saldo += valor + valor * 0.025; //Deposito mais 2.5%
                categoria = Categoria.PLATINUM;
                return true;
            }else{
                saldo += valor + valor * 0.01; //Deposito mais 1%
                return true;
            }
        }

        //Caso a conta esteja no PLATINUM
        if (categoria == Categoria.PLATINUM){
            saldo += valor + valor * 0.025; //Deposito mais 2.5%
            return true;
        }

        return false;
    }

    public boolean retirada(double valor){
        if (valor <= 0.0 && saldo - valor < 0){
            return false;
        }

        saldo -= valor;

        if (categoria == Categoria.PLATINUM && saldo < 100000){
            categoria = Categoria.GOLD;
        }

        else if (categoria == Categoria.GOLD && saldo < 25000){ 
            categoria = Categoria.SILVER;
        }

        return true;
    }

    private void verificaNroConta(String numero){
        int posTraco = numero.indexOf('-');
        String nroStr = numero.substring(0,posTraco);
        int nroConta = Integer.parseInt(nroStr);
        int verificador = Integer.parseInt(numero.substring(posTraco+1));
        
        if (nroConta < 99999 || nroConta > 999999){
            throw new IllegalNumberException();
        }
        int soma = 0;
        for(int i=0;i<nroStr.length();i++){
            soma += (Integer.parseInt(nroStr.charAt(i)+""));
        }
        if (soma != verificador){
            throw new IllegalNumberException();
        }
    }

    @Override
    public String toString() {
        return "ContaMagica [categoria=" + categoria + ", nomeCorrentista=" + nomeCorrentista + ", numero=" + numero
                + ", saldo=" + saldo + "]";
    }

    private void verificaNome(String nome){ 
        if (nome.length() < 3){
            throw new IllegalNameException();
        }
    }
}
