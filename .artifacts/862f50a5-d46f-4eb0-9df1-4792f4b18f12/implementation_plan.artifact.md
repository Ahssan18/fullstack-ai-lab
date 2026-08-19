# Clean Architecture Renaming & Refactoring Plan

Refactor the project to strictly follow Clean Architecture principles and modern Android naming conventions. This includes moving interfaces to the domain layer, grouping presentation logic, and adopting standardized class names.

## Proposed Changes

### [Domain Layer]
*   **[MOVE]** `LoginRepository` interface from `data.repository` to `domain.repository`.
*   **[RENAME]** `LoginUseCase` to `LoginUseCase` (keeping it, but verifying package consistency).

### [Data Layer]
*   **[MODIFY]** `LoginRepositoryImpl` to import the interface from the new domain package.
*   **[MOVE]** Repository implementation details to a more specific `data.repository.impl` or keep in `data.repository`.

### [Presentation Layer]
*   **[MOVE]** All UI-related packages (`ui.presentation`, `ui.Navigation`, `ui.theme`, `ui.app`) under a unified `presentation` package.
*   **[RENAME]** UI components for clarity:
    *   `Login.kt` -> `LoginScreen.kt`
    *   `Home.kt` -> `HomeScreen.kt`
    *   `Profile.kt` -> `ProfileScreen.kt`
    *   `Screens.kt` -> `Screen.kt`
*   **[RENAME]** MVI Contract classes:
    *   `LoginIntent` -> `LoginUiIntent`
    *   `LoginState` -> `LoginUiState`
    *   `LoginEffect` -> `LoginUiEffect`

### [Dependency Injection]
*   **[MODIFY]** `RepositoryModule` to reflect new package paths.

---

## Package Mapping Table

| Old Package | New Package |
| :--- | :--- |
| `ui.presentation.login` | `presentation.login` |
| `ui.presentation.home` | `presentation.home` |
| `ui.presentation.profile` | `presentation.profile` |
| `ui.Navigation` | `presentation.navigation` |
| `ui.theme` | `presentation.theme` |
| `ui.app` | `presentation.main` |
| `data.repository` (Interface) | `domain.repository` |

## Verification Plan
1.  **Build Check**: Run `./gradlew assembleDebug` to ensure all references are updated.
2.  **DI Check**: Verify Hilt graph compiles (no missing bindings).
3.  **UI Check**: Open Compose Previews for renamed files.
