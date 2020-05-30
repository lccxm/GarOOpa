# Nível de Entidades:
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
  * RepositórioDeCidades
  * RepositórioDeBairros
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
# Nível de Interface
  * TelasPassageiro
    * TelaSolicitacaoViagem
    * TelaDadosViagem
    * TelaAvaliarMotorista
  * TelasMotorista
    * TelaSelecionaViagem
    * TelaAvaliarPassageiro

# Nível de Tecnologias
  * JavaFX
    
