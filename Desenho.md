# Entidades:
* Veículos
* Motorista
* Passageiro
* Bairro
* Cidade
* Roteiro
* Viagem

# Nível de casos de uso:
* Políticas
  * VeiculoQueAtende
  * CalculoViagem
* Repositórios
  * RepositórioDeMotoristas
  * RepositórioDeClientes
* Serviços
    * ServicosDeSelecaoDeMotorista
    * ServicosDeSeleçãoDeRota
    * ServicosDeDetalhesDaViagem
      * DadosDoMotorista
      * DadosDaViagem
        * BairrosPercorridos
        * CustoDaViagem
      * Avalicoes
        * Passageiro2Motorista
        * Motorista2Passageiro
* Interface
  * TelasPAssageiro
    * TelaSolicitacaoViagem
    * TelaDadosViagem
    * TelaAvaliarMotorista
  * TelasMotorista
    * TelaSelecionaViagem
    * TelaAvaliarPassageiro


    
