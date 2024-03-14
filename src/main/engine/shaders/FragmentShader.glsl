#version 330

in vec3 color;
out vec4 fragColor;

uniform vec3 test;

void main() {
    fragColor = vec4(test, 1.0);
}