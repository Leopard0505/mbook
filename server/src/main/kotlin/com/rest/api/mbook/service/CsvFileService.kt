package com.rest.api.mbook.service

import com.rest.api.mbook.entity.Book
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.Optional

/**
 * CSVファイル Service
 */
@Service
class CsvFileService {

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
                var line = bufferedReader.readLine()
                // ヘッダ分をスキップするためbufferedReader.readLine()を2回行う
                line = bufferedReader.readLine()
                while(line != null) {
                    val cells = line.split(",")
                    // TODO ここでセル値のチェックを行う
                    // フォーマットがあるので必ず4要素（0-3）のみ
                    val book = Book(title = cells[0], original_author = cells[1], drawer = cells[2], publisher = cells[3])
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
     * 正しいフォーマットか確認する
     */
    private fun validateFormat() {
        // TODO
    }

    /**
     * ファイルサイズが上限内か確認する
     */
    private fun validateFileSize() {
        // TODO
    }
}