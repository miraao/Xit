
# Xit

Decompiled.

## 概要
某 pixl の AndroidTV 搭載チューナー用らしい視聴・録画用アプリを Decompile したもの

Google Play の概要欄には 4K 対応チューナーの型番で使用可能とは書かれていないが、UI やコード等からアプリは 4K 対応・非対応チューナー間で共通のものを利用していると思われる  
（コード中に例の 4K チューナーの型番や未発表のチューナー、OEM チューナーの型番が含まれていた（チューナーごとに条件分岐を行っていると思われる））  
特に lib フォルダ内のネイティブ C のライブラリには ACAS Chip・B-CAS・Multi2・AribCas・SoftCas など CAS 処理に関するワードが含まれている  
Decompile したところ、デバッグ用と思われる printf がかなり残っているほか、一部の関数は関数名がそのまま残っている状態だった

ただ TS に関する処理は多く見られるものの MMT/TLV に関する処理はざっと見た感じだと見当たらないのが気になるところ  
（関数名が一部しか残っていない事、必ずしも関数名に例のワードが入っているとは限らないことから、きちんと探せば見つかるかもしれない）

C 言語は全く分からないので、間違いを含んでいる可能性もある  
もしかすると何かの解析の手助けとなるかもしれないし、ならないかもしれない

## 内容物

メモ書き程度

- assets … 内部で使われている HTML・CSS・JS 類
  - CrossWalk (WebView) を使い Web サイトの要領でアプリ内に内蔵した HTML を表示させているものと思われる
  - UI は全て HTML で構成されているほか、フレームワークとして Vue.js を使っているらしい
  - Common/data の UI.dat・UI2.dat の中身は ARIB 外字対応 UD ゴシック / UD 丸ゴシックだった
  - raw フォルダにはデータ放送で利用される効果音（.ogg）が入っていた
  - BML（データ放送を記述している JavaScript 互換の言語）に関しての記述が見られた
- decompile … classes.dex を Decompile したもののうちライブラリ等を除いたアプリ本体のコード類
  - Java は中間言語に Compile されるため Decompile が容易な分、比較的正確にコードを再現できている
  - このうち airtunerjni 以下は Java Native Interface (JNI) 用のコードで、ここで後述するネイティブ C のライブラリを呼び出している模様
- lib/armeabi-v7a … C 言語ネイティブで書かれた ACAS（？）・B-CAS を復号し再生させていると思われるライブラリ群を Ghidra で Decompile したもの（本命）
  - C 言語は Java とは異なり機械語に Compile されるため、残念ながら Decompile して復元しても完全に動作するコードにはならない場合が多い
  - そのため、実際のコードとは大きくかけ離れている可能性もある
  - .so ファイルが元の binary ファイルで、.so.c はそれを Decompile したもの
  - また、実際は分割されているであろうソースコードが全て一つになっているため、相当巨大なコードになってしまっている
  - 実際は printf() ではなく nop_printf() というラッパー関数が利用されている
  - 肝心の ACAS については libairtuner.so の printf 内の ACAS Chip という記述のみだったが、アプリ自体は 4K 放送の再生に対応していると思われること、  
    もし ACAS の復号に対応していないのであればその記述を入れる必要がないことなどから、ACAS 周りの処理が記述されている可能性は高いと考えられる
    - もしかすると ACAS Chip を利用して 2K 放送を復号しているだけかもしれないが
  - HAVPF_PD_Demux_Multi2Decrypt_SetSystemKey() や ParseECM() のような関数が存在することから、B-CAS 周りの処理が記述されているのは確実
  - printf 内の文字列を見る限り、C++ ではなく C で記述されていると思われる
  - 他にも AribCas や何故か SoftCas という記述も見つかるが、これが何を意味するのかは不明
  - libairtuner.so … ECM・EMM・Multi2・Descramble 、ACAS Chip や B-CAS などの記述が大量に含まれていることから、CAS 処理の根幹を担っていると思われる
    - mxl_MxL68x_ctrl.c とあるが、チューナーの IC に関係していると思われる
    - OpenSSL に関しての記述もある
    - ACAS・Multi2 に関しての記述はこのファイルのみだった（はず）
  - libplayer_serviceAT.so … 録画・再生周りを担当しているらしい
    - CAS 処理に関する記述は見当たらず
    - ResGetCasInfo という記述はあったが詳細は不明
  - libPxDMSDaemon.so … UPnP や x509 といったネットワークに関係する記述が見られるほか、Daemon とあることからネットワーク配信に関係する処理を行っていると考えられる
    - 映像処理系統には関与していないらしい
    - DLNA に関する記述もあった
    - Chromecast 周りなのではないかと推測
  - libPxjfClientService.so … libPxDMSDaemon.so 同様にネットワークに関係する記述が多く見られるが、何をやっているのかはよくわからない
    - Camellia（NTTと三菱電機により共同開発されたブロック暗号）に関する記述が見られたが、これをネットワーク配信周りに使っているのだろうか
    - openssladapter.cc という記述が見られたが、openssladapter.cc は Chromium の WebRTC 周りのコードらしい
  - libstationtv_lt_stb_control.so …  libairtuner 同様に CAS 処理周りを行っていると思われるが、どう違うのかは不明
    - 何をやっているのかはよくわからない
    - SoftCas や CCoreAribCas が何を指しているのかは謎
    - SOFTCAS_ERROR_INVALID_PROTOCOL_NUMBER …？
    - updateEMMIndividualMessage()・updateEMMIndividualMessage() のような関数が存在する
  - libstationtv_lt_stb_stream.so … 再生周りの処理を行っていると思われるが、よくわからない
    - H264 とあるので MP4 への変換処理も行っているらしい？
    - AribCas はないが SoftCas の記述はあった
    - SetSoftCas(ISDBDemuxerImple *this,SoftCas *param_1) のような関数があるため、映像ソースを処理しているのは確実
  - libxwalkcore.so … WebView 用の CrossWalk のコア・CAS 処理との関係はなさそう
    - 本体とは関係ないファイルのため Decompile は行っていない
  - libxwalkdummy.so … CrossWalk に関係しているファイルと考えられるがなぜ dummy なのかは謎
    - libxwalkcore.so 同様、本体とは関係ないファイルのため Decompile は行っていない
- META-INF … Java 関係のメタファイルらしい
- org … ライブラリの Java プロパティファイルが入っている
- res … アイコン等の画像一式（ png・xml（svg））
- AndroidManifest.xml … Android アプリのマニフェストファイル（ binary 化されていてよめない）
- AndroidManifest.decode.xml … AndroidManifest.xml を AXMLPrinter2.jar でデコードしたもの
- bundle.properties … プロパティファイル（見る意味はない）
- classes.dex … 実行用の binary 
- classes.jar … classes.dex を dex2jar で Jar にしたもの
- classes2.dex … 実行用の binary その2（ライブラリしか入ってなかった）
- classes2.jar … classes2.dex を dex2jar で Jar にしたもの
- README.md … このりどみ
- resources.arsc … 本来は res/values/ 以下に含まれている String リソース
- resources.txt … resources.arsc を aapt でデコードしたもの
