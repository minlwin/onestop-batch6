export function generate(array: Array<any>) {
  if(array.length) {
    let arr = array.sort((a, b) => a.id - b.id)
    return arr[arr.length - 1].id
  }

  return 1
}
