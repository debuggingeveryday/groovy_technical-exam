# TECHNICAL EXAM

## Requirements
> Docker https://www.docker.com/
> Create sub-directory its up to you.
> Create {file name}.txt is required.

## Commands to run this project

### docker pull image groovy
```
docker pull groovy
```

### to run groovy file

```
docker run --rm -v "$PWD":/home/groovy/scripts -w /home/groovy/scripts groovy groovy <fromPath> <oldFile> <toPath> <newFile>

# (e.g) docker run --rm -v "$PWD":/home/groovy/scripts -w /home/groovy/scripts groovy groovy ./ orignalText.txt ./sub_directory newText.txt
```