#version 330

uniform sampler2D texture_sampler;

uniform vec3 test;
uniform float time;

in vec2 texCoord;

out vec4 fragColor;

void main() {
    float sprechiationSpazion = max(dot(test, vec3(1.0, 1.0, 1.0)) * time, 1.0);
    fragColor = texture(texture_sampler, texCoord) * sprechiationSpazion;
}