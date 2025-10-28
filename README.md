# 🎬 Movie Explorer - Cinema na Palma da Mão ✨

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Material 3](https://img.shields.io/badge/Design-Material%203-757575?style=for-the-badge&logo=material-design&logoColor=white)](https://m3.material.io)

> **🚀 PROJETO FINALIZADO E 100% FUNCIONAL! 🚀**
> 
> **Desenvolvido por Vicente de Souza** | **Instalado e Testado com Sucesso** | **Disponível no GitHub**

---

## 🌟 Sobre o Movie Explorer

**Movie Explorer** é um aplicativo Android **completo e moderno** que transforma a experiência de descobrir filmes! Desenvolvido com as **tecnologias mais avançadas** do Android:

- 🎯 **Kotlin 100%** - Linguagem moderna e segura
- 🎨 **Jetpack Compose** - Interface nativa e fluida  
- 🎭 **Material 3** - Design cinematográfico elegante
- 🌐 **API OMDb** - Base de dados mundial de filmes
- 📱 **Testado e Funcionando** - Instalado no Redmi Note 12S

### 🏆 **Destaques do Projeto**
✅ **Interface Cinematográfica** - Design inspirado em cinemas com tema escuro
✅ **Busca Inteligente** - Pesquisa em tempo real com sugestões
✅ **Detalhes Completos** - Sinopse, elenco, avaliações e muito mais
✅ **Sistema Robusto** - Retry automático e tratamento de erros
✅ **100% Funcional** - Testado e aprovado em dispositivo real

## ✨ Funcionalidades Principais

### 🔍 **Busca Inteligente**
- Pesquisa por títulos de filmes, séries e outros conteúdos
- Validação automática (mínimo 2 caracteres)
- Dicas de busca contextuais
- Retry automático em falhas de rede (até 3 tentativas)
- Botão de limpar busca com animações

### 🎭 **Interface Moderna & Responsiva**
- Design Material 3 com paleta cinematográfica
- Animações suaves em todas as transições
- Loading indicators personalizados
- Estados de erro inteligentes
- Pull-to-refresh funcional

### 📖 **Detalhes Completos de Filmes**
- Pôster em alta qualidade com fallback
- Informações básicas (ano, classificação, duração)
- Avaliações IMDb com sistema visual
- Sinopse completa e bem formatada
- Elenco, diretor e roteirista
- Gêneros com chips coloridos
- Informações técnicas (idioma, país, prêmios)

### ⚡ **Performance & Confiabilidade**
- Carregamento assíncrono de imagens com Coil
- Estados reativos com ViewModel
- Gerenciamento robusto de erros de rede
- Cache inteligente de dados
- Interface fluida sem travamentos

## 🛠 Stack Tecnológica Avançada

| Tecnologia | Descrição | Versão |
|------------|-----------|--------|
| **Kotlin** | Linguagem moderna e concisa | 1.9+ |
| **Jetpack Compose** | UI declarativa e reativa | 2023.10 |
| **Material 3** | Sistema de design moderno | Última |
| **Retrofit** | Cliente HTTP robusto | 2.9.0 |
| **Coil Compose** | Carregamento otimizado de imagens | 2.4.0 |
| **ViewModel** | Arquitetura MVVM reativa | 2.7.0 |
| **Coroutines** | Programação assíncrona | 1.7.3 |
| **GSON** | Serialização JSON eficiente | 2.9.0 |
| **OkHttp** | Interceptors e logging | 4.11.0 |

## � Experiência do Usuário

### 🌟 **Design Cinematográfico**
- Paleta de cores inspirada no cinema (dourado, vermelho, tons quentes)
- Tipografia Material 3 otimizada para legibilidade
- Ícones e emojis temáticos relacionados a filmes
- Animações suaves que não sobrecarregam a interface

### 🔄 **Estados Inteligentes**
- **Loading**: Indicadores animados com mensagens contextuais
- **Sucesso**: Lista organizada com contador de resultados
- **Erro**: Mensagens específicas com opções de retry
- **Vazio**: Telas de boas-vindas e dicas úteis

### 📱 **Responsividade**
- Layout adaptável a diferentes tamanhos de tela
- Cards com espaçamento otimizado
- Scroll suave em listas longas
- Hierarquia visual clara

## 🏗 Arquitetura Robusta

O projeto implementa **Clean Architecture** com padrões modernos:

```
app/src/main/java/com/movieexplorer/
├── data/                 # Modelos de dados
│   └── Movie.kt         # Data classes (Movie, MovieSearchResponse, MovieDetails)
├── network/             # Camada de rede
│   ├── MovieApi.kt      # Interface da API
│   └── RetrofitClient.kt # Configuração do Retrofit
├── ui/                  # Interface do usuário
│   ├── theme/          # Tema Material 3
│   ├── MainScreen.kt   # Tela principal
│   ├── SearchBar.kt    # Componente de busca
│   ├── MovieCard.kt    # Card de filme
│   ├── MovieList.kt    # Lista de filmes
│   └── MovieDetailsScreen.kt # Tela de detalhes
├── viewmodel/          # Lógica de negócio
│   └── MovieViewModel.kt # ViewModel principal
└── MainActivity.kt     # Atividade principal
```

## 🚀 Como Executar

### Pré-requisitos

1. **Android Studio** (versão mais recente)
2. **Chave da API OMDb** (gratuita)

### Configuração da API

1. Acesse [https://www.omdbapi.com/apikey.aspx](https://www.omdbapi.com/apikey.aspx)
2. Solicite sua chave gratuita (chegará por e-mail)
3. Abra o arquivo `app/src/main/java/com/movieexplorer/network/RetrofitClient.kt`
4. Substitua `YOUR_API_KEY` pela sua chave:

```kotlin
private const val API_KEY = "sua_chave_aqui"
```

### Executando o App

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/movie-explorer-app.git
```

2. Abra o projeto no Android Studio

3. Configure sua API key (passo anterior)

4. Sincronize o projeto (Gradle Sync)

5. Execute no dispositivo ou emulador

## 📱 Screenshots

### Tela Principal
- Interface limpa com campo de busca
- Estado inicial com mensagem de boas-vindas
- Lista de resultados com pôsteres e informações básicas

### Tela de Detalhes
- Pôster em alta qualidade
- Informações completas do filme
- Avaliação IMDb com ícone de estrela
- Sinopse e detalhes técnicos

### Estados da Interface
- **Loading**: Indicador de carregamento durante buscas
- **Erro**: Mensagens de erro amigáveis para problemas de conexão
- **Vazio**: Orientação quando não há resultados

## 🎯 Recursos Implementados

### ✅ Requisitos Obrigatórios
- [x] Consumo da API OMDb com Retrofit
- [x] Interface em Jetpack Compose
- [x] Estados reativos com `mutableStateOf`
- [x] ViewModel para gerenciamento de estado
- [x] Busca por título de filme
- [x] Exibição de pôsteres com Coil
- [x] Lista de resultados
- [x] Tratamento de erros

### 🌟 Recursos Extras (Bônus)
- [x] Tela de detalhes completos do filme
- [x] Modo claro/escuro automático (Material 3)
- [x] Animações de carregamento
- [x] Tratamento robusto de erros
- [x] Interface responsiva e moderna
- [x] Estados de carregamento diferenciados

## 🧪 Testando o App

### Exemplos de Busca
- `batman` - Filmes do Batman
- `avengers` - Filmes dos Vingadores  
- `star wars` - Saga Star Wars
- `matrix` - Trilogia Matrix

### Casos de Teste
- Busca com resultados múltiplos
- Busca sem resultados
- Busca com erro de rede
- Visualização de detalhes
- Navegação entre telas

## 📚 Conceitos Demonstrados

| Conceito | Implementação |
|----------|---------------|
| **Jetpack Compose** | Interface declarativa com Composables |
| **State Management** | Estados reativos com `mutableStateOf` |
| **MVVM Architecture** | ViewModel separando UI da lógica |
| **API Consumption** | Retrofit + Coroutines para chamadas REST |
| **Image Loading** | Coil para carregamento assíncrono |
| **Error Handling** | Tratamento de exceções e estados de erro |
| **Material Design** | Componentes Material 3 |
| **Navigation** | Navegação entre telas com estado |

## � Como Usar

### 📥 **Instalação Rápida**
1. Clone este repositório:
   ```bash
   git clone https://github.com/Souza371/movie-explorer-app.git
   ```

2. Abra no **Android Studio** e aguarde sincronização

3. Execute no emulador ou dispositivo (API 24+)

### 📱 **Guia de Uso**

#### 🔍 **Buscar Filmes**
- Digite pelo menos 2 caracteres na barra de busca
- Use títulos em inglês para melhores resultados  
- Toque em "Buscar" ou pressione Enter
- Aguarde o carregamento com retry automático

#### 📋 **Explorar Resultados**
- Veja lista de filmes com posters e informações básicas
- Contador mostra quantos resultados foram encontrados
- Toque em qualquer filme para detalhes completos
- Use botão de atualizar para refresh

#### 📖 **Detalhes Completos**
- **Header**: Poster, título, ano, classificação, duração
- **Avaliações**: Rating IMDb com sistema visual
- **Sinopse**: Descrição completa do filme
- **Elenco**: Diretor, atores principais e roteirista  
- **Técnico**: Idioma, país, prêmios e produção

### 🎯 **Funcionalidades Avançadas**

#### ⚡ **Retry Automático**
- Falhas de rede são automaticamente recuperadas
- Até 3 tentativas com delay inteligente
- Mensagens contextuais sobre o progresso

#### 🎨 **Estados Inteligentes**
- Loading personalizado com animações
- Mensagens de erro específicas (rede, timeout, etc.)
- Telas vazias com dicas úteis
- Transições suaves entre estados

#### 📱 **Interface Responsiva**
- Design Material 3 cinematográfico
- Animações fluidas e não invasivas  
- Scroll otimizado para listas longas
- Cards com elevação e interações

## �👥 Autores

**Vicente** - Desenvolvedor Principal
- GitHub: [@Souza371](https://github.com/Souza371)
- Email: vicentedesouza@gmail.com

**GitHub Copilot** - Assistente de Desenvolvimento
- Ajuda com código, arquitetura e melhores práticas
- Implementação de funcionalidades avançadas

## �‍💻 Desenvolvedor

<div align="center">

### **Vicente de Souza** 
*Desenvolvedor Android & Entusiasta de Cinema*

[![GitHub](https://img.shields.io/badge/GitHub-@Souza371-181717?style=for-the-badge&logo=github)](https://github.com/Souza371)
[![Email](https://img.shields.io/badge/Email-vicentedesouza@gmail.com-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:vicentedesouza@gmail.com)

🎯 **"Transformando ideias em aplicativos funcionais"**

**🚀 Projeto finalizado em 28/10/2025 - 100% funcional e testado! 🚀**

</div>

---

## 🏆 Status do Projeto

✅ **PROJETO CONCLUÍDO COM SUCESSO!**
- ✅ App desenvolvido e funcionando 100%
- ✅ Instalado no dispositivo Redmi Note 12S  
- ✅ Testado todas as funcionalidades
- ✅ Código salvo no GitHub
- ✅ Documentação completa
- ✅ APK gerado e funcionando

---

## 📄 Licença

Este projeto está licenciado sob a **MIT License** - uso livre para projetos pessoais e comerciais.

## 🙏 Agradecimentos

### 🎬 **Tecnologias que Tornaram Este Projeto Possível**
- **[OMDb API](https://www.omdbapi.com/)** - Base de dados mundial de filmes
- **[Material Design](https://material.io/)** - Sistema de design excepcional
- **[Android Jetpack](https://developer.android.com/jetpack)** - Ferramentas modernas
- **[Kotlin](https://kotlinlang.org/)** - Linguagem elegante e poderosa

---

<div align="center">

### 🎬 **Movie Explorer - Seu Cinema Pessoal na Palma da Mão!**

**Desenvolvido com ❤️ e muito ☕ usando as melhores práticas do Android moderno**

⭐ **Se curtiu o projeto, deixe uma estrela no GitHub!** ⭐

[![Stars](https://img.shields.io/github/stars/Souza371/movie-explorer-app?style=social)](https://github.com/Souza371/movie-explorer-app/stargazers)

---

*Kotlin • Jetpack Compose • Material 3 • OMDb API • MVVM • Vicente de Souza*

**Última atualização: 28 de Outubro de 2025** 🎉

</div>
