# Colosseum Project - Ludus

Microservice application part of the Colosseum Project and responsible for the management of the gladiators.

- [Colosseum Project - Ludus](#colosseum-project---ludus)
  - [License](#license)
  - [Contributing](#contributing)
  - [Build OCI image](#build-oci-image)
  - [Generate API docs](#generate-api-docs)

---

## License

This application is released under the terms of the MIT license.
See [LICENSE](LICENSE) for more information or see <https://opensource.org/licenses/MIT>.

## Contributing

If you want to contribute to the project, please read the [CONTRIBUTING file](CONTRIBUTING.md).

## Build OCI image

Images can be built using the `bootBuildImage` Gradle task.
To build an image, run:

```sh
./gradlew bootBuildImage --imageName=colosseum-project/ludus
```

_It creates an optimized image using [Buildpack](https://buildpacks.io/)._

## Generate API docs

_The documentation will be generated under `build/api-docs`._

Generate the documentation:

```sh
./gradlew build asciidoctor
```

Then, you can open [index.html](build/api-docs/index.html) with your favorite browser.
