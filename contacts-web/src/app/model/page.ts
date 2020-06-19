export class Page<T> {

  public content: T[] = []
  public pageable: {
    pageNumber: 0,
    pageSize: 0,
    offset: 0,
    unpaged: false,
    paged: false
  }
  public last: boolean = false
  public totalPages: number = 0
  public totalElements: number = 0
  public numberOfElements: number = 0
  public first: boolean = false
  public size: number = 0
  public number: number = 0
  public empty: boolean = false
}
