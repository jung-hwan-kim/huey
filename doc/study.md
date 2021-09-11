
# Kube
```
k create -f k8s/deployment.yaml
k create -f k8s/service.yaml
k get svc,deployment,pod
k apply -f k8s/deployment.yaml

k exec -it {pod_name} -- /bin/bash
k logs {pod_name} =f
k delete -f k8s/service.yaml
k delete -f k8s/deployment.yaml

```
# Helm

```shell
helm install huey chart --debug

helm delete --purge huey

helm reset --force

```