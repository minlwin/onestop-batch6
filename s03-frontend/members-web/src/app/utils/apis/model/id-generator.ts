export function generate(array: Array<any>) {
  let arr = array.sort((a, b) => a.id - b.id)
  return arr[arr.length - 1].id
}
