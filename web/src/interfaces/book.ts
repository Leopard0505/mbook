export interface BookInfo {
  book_info_id: number
  book_id: number
  title: string
  release_date: string
  special_edition: boolean
}

export interface Book {
  book_id: number
  title: string
  original_author: string
  drawer: string
  publisher: string
  bookInfos: BookInfo[]
}