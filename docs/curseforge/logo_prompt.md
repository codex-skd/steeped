# Steeped — Logo de CurseForge

> Prompt para generar el avatar del proyecto en CurseForge (400×400). Mismo estilo que la familia de logos del ecosistema: fondo violeta con nebulosa, marco dorado, pixel-art y un único color de acento por mod.

## Prompt

```
Pixel-art mod icon for a Minecraft NeoForge mod called "Steeped". 400x400 square, perfectly centered.

SUBJECT: a cosy ceramic teapot, round and slightly chubby, glazed in warm cream with a deep tea-green band
around the belly, a curved spout and a small handle, a lid with a tiny knob. A soft curl of white steam
rises from the spout and the lid. Beside it, a small matching cup on a saucer holding amber tea with a
little steam, and a sprig of fresh tea leaves (three glossy green leaves on a short brown stem) resting
against the teapot.

COMPOSITION: the teapot is the clear focal point, filling most of the frame, seen from a slight
three-quarter angle. The cup sits front-right, the leaves front-left. Everything rests on a small dark
wooden tray; nothing floats.

FRAME: an ornate golden border with filigree corners, echoing a gilded frame. A few small warm sparkles
and four-pointed star glints drift around the scene.

BACKGROUND: deep near-black violet with a soft purple nebula, darker at the corners so the gold frame
and the teapot both read clearly.

PALETTE: gold #d4af6a for the frame, cream #f1e3c6 and tea-green #5f9b4a through #2f5d2a for the teapot
and leaves, amber #c98a2b for the tea, white #f7fbff for the steam, violet #2a1a3e for the background.
One accent colour only: tea green.

STYLE: crisp pixel art, large readable pixels, limited palette, strong contrast, 16-bit and 32-bit
pixel art feel. Readable at 64x64 - silhouette first, detail second.

AVOID: photorealism, 3D render, smooth gradients, anti-aliasing, thin hairlines, text, letters, UI
elements, people or faces, red or blue accents, cluttered background.
```

## Por qué cada elemento está pedido

| Elemento | Razón |
|---|---|
| Tetera como foco | Es el objeto central del mod y la silueta más reconocible a 64 px |
| Taza y hojas | Resumen del bucle de juego: árbol → hoja → tetera → taza |
| Verde té como único acento | Regla de la familia: dorado = casa Codex, violeta = fondo, un acento por mod |
| Marco dorado + nebulosa violeta | Anclan el logo a la familia de logos publicados |
| "Readable at 64x64" | CurseForge muestra el avatar en tarjetas de ~100 px |

## Después de generarla

1. Comprobar que es 400×400 y que se lee a 100 px (tetera y vapor reconocibles).
2. Subirla como avatar del proyecto en CurseForge.
3. Opcionalmente subirla a `node-files.skdragons.com/uploads/MINECRAFT/Codex/`.
4. No versionarla en el repo.
5. Para el icono dentro del juego (`icon.png` 64×64, `logoFile` en `neoforge.mods.toml`) pedir una versión sin marco; hoy el mod no lleva logo.
