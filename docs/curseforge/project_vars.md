# CurseForge — Variables del proyecto

> Leídas por `codex-docs/scripts/curseforge-upload.ps1`. **No versionar tokens reales**: el API token de cuenta está en `codex-docs/secrets.md`; copiarlo aquí solo en local cuando se vaya a subir.

project_id = 1740348
game_versions = PENDIENTE (incluir siempre Client y Server + 1.21.1 + NeoForge; consultar IDs con la API de game versions)
release_type = beta
relations =

## Proyecto

| Variable | Valor |
|----------|-------|
| `mod_id` | `steeped` |
| `display_name` | `Steeped` |
| `classId` | `6` (Mods) |
| `category` | Food (principal); secundaria opcional: World Gen |
| `license` | MIT |
| `source_url` | `https://gitlab.com/stalking-dragons/minecraft/steeped` |

### Summary (campo *Summary* del proyecto, editable en la web)

```
Tea trees, teapots and a cupboard of brews, each with its own effect. A NeoForge 1.21.1 port of Simply Tea.
```

(105 caracteres. Límite de CurseForge: 200.)

### Alternativas de summary

- `Grow tea in the wild, boil it in a teapot and sip your way to new effects. A NeoForge 1.21.1 port of Simply Tea.`
- `A cosy tea mod: tea trees, teapots, eight brews and five custom effects. Port of Simply Tea for NeoForge 1.21.1.`

### Pendiente antes de crear el proyecto

- ~~Logo 400×400~~ hecho: `logo_400.png` (y original `logo_1254.png`), ambos solo locales.
- Proyecto creado en CurseForge (ID 1740348). Verificar slug final.
- Revisar la descripción cuando el port esté verificado in-game: las características listadas vienen del mod original, no de este port.
- Licencia en la web: MIT. Enlace al código fuente y atribución a Elucent y KnightMiner.
