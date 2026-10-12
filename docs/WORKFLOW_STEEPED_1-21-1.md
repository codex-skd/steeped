# Flujo de trabajo — Steeped (NeoForge)

> **Versión del workflow**: 1.21.0 (codex-docs)
> Este archivo pertenece al proyecto **Steeped**. Cambios aquí solo afectan a este proyecto.
> **Trabaja directamente con este archivo**: es el workflow operativo del mod, autocontenido. No leas `codex-docs/WORKFLOW_AGENT.md` ni `WORKFLOW_GENERIC.md` de forma rutinaria.
> On-demand: `codex-docs/reference/CURSEFORGE.md`, `codex-docs/reference/GRAPHIFY.md`, `codex-docs/reference/REPO_SETUP.md` (setup único de repo — pendiente), `codex-docs/reference/WIKI.md`.

## Específico del mod

| Campo | Valor |
|---|---|
| **Mod ID** | `steeped` |
| **Clase principal** | `com.skd.steeped.Steeped` |
| **Display name** | `Steeped` |
| **Versión Minecraft** | `1.21.1` |
| **Versión NeoForge** | `21.1.249` (no subir sin pedirlo) |
| **Rama de trabajo** | `minecraft/1.21.1/neoforge-21.1.249/production` |
| **Repositorio GitLab** | `https://gitlab.com/stalking-dragons/minecraft/steeped.git` |
| **Paquete base** | `com.skd.steeped` |
| **Licencia** | MIT (port de Simply Tea, Elucent / KnightMiner) |
| **CurseForge project id** | pendiente |

## Qué es

Port de **Simply Tea 2.7.0** (Forge 1.20.1, MIT) a NeoForge 1.21.1. Fuente original en `temp/original/` (gitignored, solo lectura). Primer mod del bloque 1 de `warhost/docs/PLAN_DE_RUTA.md`; sirve también de prueba del flujo de port con OpenCode.

## Restricciones activas (viajan con cualquier delegación a OpenCode)

- **Nunca subir versiones** de dependencias, mods ni NeoForge.
- **Nunca borrar ficheros** sin que se pida; `temp/original/` no se toca.
- OpenCode no hace `git push` ni sube nada a CurseForge.
- Sin residuos del original: paquete `knightminer.simplytea` → `com.skd.steeped`, ids `simplytea:` → `steeped:`, lang, modelos, datos.
- Atribución en README, NOTICE y `neoforge.mods.toml`.

## Convenciones, ramas, versionado, commits

Las del grupo (`WORKFLOW_GENERIC`): snake_case `steeped`, clase `Steeped`, Title Case `Steeped`. Rama única `production`. Versión `0.0.0-beta.X`; JAR `steeped-1.21.1-neoforge-21.1.249-<version>.jar`. Commits Conventional Commits en inglés con `v<version>`. Sin tags.

## Flujo por tarea

1. Desarrollo → `./gradlew.bat build` → commit → push.
2. Verificar siempre con `runServer` (arranque puro) además de `runClient`; render y jugabilidad no se dan por buenos sin probar in-game.
3. CurseForge solo con confirmación expresa.

## Idioma

Código, logs, commits, README y CurseForge en inglés. docs/ y CHANGELOG en castellano.
