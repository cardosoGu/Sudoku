```markdown
# Sudoku Java Console & GUI

Projeto de Sudoku em Java com interface GUI. Permite inserir/remover números, resetar e checar andamento e finalizar o jogo com verificação com regras do jogo.

## Funcionalidades

- Inicializar um novo jogo com valores fixos e esperados.
- Inserir números em células livres (1 a 9).
- Remover números de células livres.
- Resetar o tabuleiro.
- Verificar status do jogo: `Non Started`, `Incomplete`, `Complete`.
- Interface de console simples e inputs via `NumberText` para GUI.
- Validação de números, proteção de células fixas e prevenção de erros de input.

## Estrutura do Projeto

```
src/
├─ model/
│  ├─ Board.java            # Lógica do tabuleiro
│  ├─ Space.java            # Representa uma célula
│  ├─ GameStatusEnum.java   # Enum de status do jogo
├─ protocol/
│  ├─ SpaceProtocol.java
├─ service/
│  ├─ BoardService.java
│  ├─ NotifierService.java
│  ├─ EventEnum.java
│  ├─ EventListener.java
├─ ui/
│  ├─ buttons/
│  │  ├─ ButtonCheckGameStatus.java
│  │  ├─ ButtonReset.java
│  │  ├─ FinishGameButton.java
│  ├─ frame/
│  │  ├─ MainFrame.java
│  ├─ input/
│  │  ├─ NumberText.java
│  │  ├─ NumberText.java
│  ├─ panel/
│  │  ├─ MainPanel.java
│  │  ├─ SudokuSelector.java
│  ├─ scream/
│  │  ├─ MainScream.java
├─ utils/
│  ├─ BoardTemplate.java
│  ├─ NumsFixedsAndExpecteds.java
├─ UIMain.java
```

## Como Rodar

1. Clone o repositório:

```bash
git clone https://github.com/cardosoGu/Sudoku.git
```

2. Compile:

```bash
javac -d out src/**/*.java
```

3. Execute:

```bash
java -cp out UIMain
```

Siga as instruções do menu para:

- Inserir ou remover números  
- Resetar ou finalizar o jogo  
- Visualizar status do tabuleiro  

## Observações

- Células marcadas como `fixed` não podem ser alteradas pelo usuário.  
- Apenas números entre 1 e 9 são válidos.  
- Campo customizado `NumberText` lida com validação, limpeza de células e integração com o modelo.
```
