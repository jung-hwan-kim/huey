# Docker

### Building Images
```bash
docker build . -t junghwankim/huey:1.3
```

### Running Docker i.e. Creating Container & Starting it! 
```bash
docker create --name huey-1.3 -p 40000:40000
docker start huey-1.3
docker stop huey-1.3
```
### Publishing
```bash
docker push junghwankim/huey:1.3
```

### Optional
```bash
docker tag huey junghwakim/huey:1.3
# Combining both "create" & "start"
docker create --name huey-1.3 -p 40000:40000

```