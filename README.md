# 🎬 Movie Explorer - Seu App de Filmes 100% Funcional

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-orange.svg)](https://developer.android.com/jetpack/compose)
[![Material 3](https://img.shields.io/badge/Design-Material%203-purple.svg)](https://m3.material.io)

## 📱 Sobre o Projeto

**Movie Explorer** é um aplicativo Android moderno e **100% funcional** desenvolvido em **Kotlin** com **Jetpack Compose** que consome a API do **OMDb (Open Movie Database)** para buscar e exibir informações completas sobre filmes, séries e outros conteúdos audiovisuais.

O app oferece uma experiência cinematográfica completa com busca inteligente, retry automático, animações suaves e design Material 3 inspirado no mundo do cinema.

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

## 📄 Licença

Este projeto foi desenvolvido como parte do curso de desenvolvimento Android e está licenciado sob a [MIT License](LICENSE).

## 🙏 Agradecimentos

- [OMDb API](https://www.omdbapi.com/) pela API gratuita de filmes
- [Material Design](https://material.io/) pelas diretrizes de design
- [Android Developers](https://developer.android.com/) pela documentação

---

⭐ **Desenvolvido com ❤️ para o projeto final de Android Development**
