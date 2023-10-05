FROM gitpod/workspace-full

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh \
    && sdk update \
    && sdk install java 17.0.8.1-tem \
    && sdk default java 17.0.8.1-tem"
