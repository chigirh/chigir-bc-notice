# Application仕様書
## API
### API spec
see 'openapi/bc-notice-api.yaml'
### demo API
description:it is demo.

### mail API
description:ビットコインの値段の変動を通知するためのメールアドレスに関するAPI

## Batch
### BitCoinNotice
description:ビットコインの値段に5000円以上の変動があった場合は`mail API`で登録されているメールアドレスに通知する。

1. BitBank APIに現在のビットコインの売値を問い合わせて取得する。
2. 前回バッチ起動時に取得したビットコインの売値を取得する。前回の取得データがない場合は差分なしとする。
3. 前回バッチ起動時の売値から500円以上の変動があった場合は、`mail API`で登録されているメールアドレスに現在の売値と前回からの差分を通知する。
4. 現在のビットコインの売値をインメモリストアに登録する。

BitBank API:https://github.com/bitbankinc/bitbank-api-docs
