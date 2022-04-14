package com.rest.api.mbook.service

import com.rest.api.mbook.entity.Book
import com.rest.api.mbook.exception.IllegalFileFormatException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.Optional
import java.util.Objects
import kotlin.collections.ArrayList

/**
 * CSVファイル Service
 */
@Service
class CsvFileService {

    companion object {
        const val HEADER_TEXT_TITLE: String = "タイトル"
        const val HEADER_TEXT_ORIGINAL_AUTHOR: String = "原作者"
        const val HEADER_TEXT_DRAWER: String = "作画"
        const val HEADER_TEXT_PUBLISHER: String = "発売日"
    }

    /**
     * logger ロガー
     */
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * CSVファイルを読み込む
     */
    fun read(file: MultipartFile): Optional<List<Book>> {
        val lines = ArrayList<Book>()
        try {
            BufferedReader(InputStreamReader(file.inputStream, StandardCharsets.UTF_8)).use { bufferedReader ->
                this.validateTemplateFile(bufferedReader.readLine())
                // ヘッダ分をスキップするためbufferedReader.readLine()を2回行う
                var line = bufferedReader.readLine()
                while(line != null) {
                    val cells = line.split(",")
                    // フォーマットがあるので必ず4要素（0-3）のみ
                    val book = Book(title = this.getCellValue(cells[0]),
                                    original_author = this.getCellValue(cells[1]),
                                    drawer = this.getCellValue(cells[2]),
                                    publisher = this.getCellValue(cells[3]))
                    lines.add(book)
                    line = bufferedReader.readLine()
                }
            }
        } catch (e: IOException) {
            logger.warn("ファイルが読み込めません")
            logger.warn(e.message)
            throw RuntimeException("ファイルが読み込めません", e)
        } catch (e: IndexOutOfBoundsException) {
            logger.error(e.message)
            throw RuntimeException("システムエラー", e)
        }
        return Optional.of(lines)
    }

    /**
     * セル値にnullがあれば空を返す
     * @param value: String
     * @return セル値
     */
    private fun getCellValue(value: String): String {
        return if (Objects.nonNull(value)) value else ""
    }

    /**
     * csv形式か検証する
     */
    private fun validateFileFormat() {
        // TODO csv形式かチェックする
    }

    /**
     * 正しいテンプレートファイルか検証する
     */
    private fun validateTemplateFile(line: String) {
        // テンプレートファイルの形式かチェックする
        val headers = line.split(",")
        if (headers.size == 4
            && HEADER_TEXT_TITLE.equals(headers[0])
            && HEADER_TEXT_ORIGINAL_AUTHOR.equals(headers[1])
            && HEADER_TEXT_DRAWER.equals(headers[2])
            && HEADER_TEXT_PUBLISHER.equals(headers[3])
        ) {
            return
        }
        throw IllegalFileFormatException("CSVファイルの形式が違います。登録用のテンプレートファイルを使用してください。")
    }

    /**
     * ファイルサイズが上限内か検証する
     */
    private fun validateFileSizeLimit() {
        // TODO
    }
}