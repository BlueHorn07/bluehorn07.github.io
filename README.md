# bluehorn07.github.io

## Setup

맥북에 기본으로 설치된 ruby 버전을 사용하면 안 됩니다.

```bash
$ brew install rbenv
$ rbenv install 3.2.0
$ rbenv local 3.2.0
$ rbenv rehash
$ ruby --version
ruby 3.2.0 (2022-12-25 revision a528908271) [arm64-darwin24]
```

```bash
$ gem install bundler
$ gem install jekyll
```

```bash
$ bundle install
```

캐시 클린

```bash
# 기존 설치 기록 지우기
rm -rf vendor/
rm -f Gemfile.lock

# bundle 캐시 삭제
bundle clean --force

# 시스템 gem 캐시 삭제
gem cleanup
```

## Serve

```bash
$ bundle exec jekyll serve
```

